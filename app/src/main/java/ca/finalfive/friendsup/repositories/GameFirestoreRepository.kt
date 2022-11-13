package ca.finalfive.friendsup.repositories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.models.Game
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.models.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class GameFirestoreRepository {

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
        val documentSnapshot = firestore.collection(collectionName).document(gameID)
            .addSnapshotListener { snapshot, error ->
                // if the error is null
                val response = if (error == null) {

                    var game by mutableStateOf<Game?>(null)
                    snapshot?.let { gameSnapshot ->
                        Log.d("LLAMA_Game", gameSnapshot.data.toString())
                        game = gameSnapshot.toObject()
                    }
                    Log.d("LLAMA_game", game.toString())
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
                    documentSnapshot.remove()
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