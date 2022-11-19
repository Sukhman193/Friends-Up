package ca.finalfive.friendsup.repositories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.models.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

/**
 * Repository for getting updates regarding the game
 * and sending messages
 */
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
                        game = gameSnapshot.toObject<Game>()
                        // TODO: ASK YUDHVIR Y THIS IS NOT WORKIN
                        game?.isGameEnded = gameSnapshot.get("isGameEnded").toString() == "true"
                        game?.isGameStarted = gameSnapshot.get("isGameStarted").toString() == "true"
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
            }
            // Success
            null
        }
    }

    /**
     * Add message to the database
     * @param username Username of the user
     * @param gameID game id of the game
     * @param gameMode Game mode of the current game
     * @param gameOption game option's answer to add the user to
     */
    fun sendAnswerSelected(username: String, gameID: String, gameMode: String, gameOption: GameQuestionOption) {
        // Get the collection name
        val collectionName = GameMode.getGameCollection(gameMode)
        // Get game document reference
        val docRef = this.firestore.collection(collectionName).document(gameID)
        // start a transaction
        this.firestore.runTransaction { transaction ->
            // Get the game snapshot
            val snapshot = transaction.get(docRef)
            // If the game doesn't exist than return
            // get the game
            val game: Game = snapshot.toObject() ?: return@runTransaction
            // Get the list of the game content
            val gameContentList: List<GameModeContent> = game.gameContent
            // Get the games options for the current question
            val gameOptions: List<GameQuestionOption> = game.gameContent[game.gameProgress].questionOptions
            // Get the index of the selected option
            val optionSelectedIndex = gameOptions.indexOfFirst { it.optionText == gameOption.optionText }
            // get the list of the selected by list of the option selected
            val selectedBy = gameOptions[optionSelectedIndex].selectedBy.toMutableList()
            // add the username to the list
            selectedBy.add(username)
            // Update the gameContent
            gameContentList[game.gameProgress].questionOptions[optionSelectedIndex].selectedBy = selectedBy
            // Update the document
            transaction.update(docRef, "gameContent", gameContentList)
        }
    }

    /**
     * Handle game progress, which will move to the next game content
     * @param gameProgress game progress to update to
     * @param gameMode current game mode being played
     * @param gameID id of the current game
     */
    fun handleGameProgress(gameProgress: Int, gameMode: String, gameID: String) {
        // Get the collection name
        val collectionName = GameMode.getGameCollection(gameMode)
        // Get game document reference
        val docRef = this.firestore.collection(collectionName).document(gameID)

        this.firestore.runTransaction { transaction ->
            // Get the game snapshot
            val snapshot = transaction.get(docRef)
            // if the game doesn't exist than end the transaction
            snapshot.toObject<Game>() ?: return@runTransaction
            // Check if game is not null
            // This is never going to be false
            transaction.update(docRef, "gameProgress", gameProgress)

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