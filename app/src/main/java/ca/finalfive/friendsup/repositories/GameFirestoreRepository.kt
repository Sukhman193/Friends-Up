package ca.finalfive.friendsup.repositories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.models.Chat
import ca.finalfive.friendsup.models.Game
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.models.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

class GameFirestoreRepository {

    /**
     * Firestore instance
     */
    private val firestore = FirebaseFirestore.getInstance()

    /**
     * Get realtime updates from the game selected
     * @param gameID ID of the game to look for
     * @param gameMode gameMode for the game
     */
    fun getGame(gameID: String, gameMode: String) = callbackFlow {
        // Get the collection name
        val collectionName = GameMode.getGameCollection(gameMode)

        // add listener for the document of the game
        val gameDocumentSnapshot = firestore.collection(collectionName).document(gameID)
            .addSnapshotListener { snapshot, error ->
                // if the error is null
                val response = if (error == null) {

                    var game by mutableStateOf<Game?>(null)
                    // Get the snapshot
                    snapshot?.let { gameSnapshot ->
                        // Convert the snapshot to a game object
                        game = gameSnapshot.toObject()
                    }

                    // Value of the response
                    Resource.Success(game)
                } else {
                    // Value of the response
                    Resource.Error("Failed to load books", error)
                }
                // Add element to the listener
                this.trySend(response).isSuccess
            }
            // Remove the snapshot listener
            awaitClose {
                gameDocumentSnapshot.remove()
            }
    }

    /**
     * Add message to the database
     * @param username Username of the user
     * @param icon Profile icon of the user
     * @param content Content of the message
     * @param gameID game id of the game
     * @param gameMode Game mode of the current game
     */
    fun sendMessage(username: String, icon: String, content: String, gameID: String, gameMode: String ) {
        // Get the collection name
        val collectionName = GameMode.getGameCollection(gameMode)

        // Get game document reference
        val docRef = firestore.collection(collectionName).document(gameID)

        // start a transaction
        firestore.runTransaction { transaction ->
            // Get the game snapshot
            val snapshot = transaction.get(docRef)
            // convert game to game object
            val game: Game? = snapshot.toObject()

            // Check if game is not null
            // This is never going to be false
            if(game != null) {
                // get the chat list already there
                val chatList: MutableList<Chat> = game.chatRoom.toMutableList()
                // add the new chat to the list
                chatList.add(Chat(
                    id = UUID.randomUUID().toString(),
                    sentBy = username,
                    icon = icon,
                    content = content,
                ))
                // Update the document
                transaction.update(docRef, "chatRoom", chatList.toList())
            } else {
                // This error will throw if the game is not found
                Log.d("LLAMA", "SNAPSHOT DOESN\"T EXIST")
            }
            // Success
            null
        }
    }

    companion object {
        /**
         * Singleton for the class
         */
        private var INSTANCE: GameFirestoreRepository? = null

        /**
         * Get instance for the game firestore repository
         */
        fun getInstance(): GameFirestoreRepository {
            // Check if an instance already exists
            if(INSTANCE == null) {
                INSTANCE = GameFirestoreRepository()
            }
            // return the instance
            return INSTANCE!!
        }

    }


}