package ca.finalfive.friendsup.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.friendsup.models.Game
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.repositories.GameApolloRepository
import ca.finalfive.friendsup.repositories.GameFirestoreRepository
import kotlinx.coroutines.launch
import java.util.*

/**
 * Game view model
 */
class GameViewModel: ViewModel() {
    /**
     * Id of the game to be joined
     */
    private var gameID: String? by mutableStateOf(null)

    /**
     * Username selected by the user
     */
    var username: String? by mutableStateOf(null)

    /**
     * Random user profile picture
     */
    var profilePicture by mutableStateOf("https://picsum.photos/seed/${ UUID.randomUUID() }/200")

    /**
     * Game object for the gameplay
     */
    var game: Game? by mutableStateOf(null)

    /**
     * Apollo client repository
     */
    private val gameApolloRepository = GameApolloRepository.getInstance()

    /**
     * Game repository for getting firestore updates
     */
    private val gameFirestoreRepository = GameFirestoreRepository.getInstance()

    /**
     * Helper function to check whether the game has been created
     */
    var createGameRoomCalled by mutableStateOf(false)

    /**
     * Join a new game
     * @param username Username selected by the user
     * @param gameMode Game play mode selected by the user
     */
    fun joinGame(username: String = "Jack", gameMode: String = GameMode.TRIVIA) {
        // Set the username for the user
        this.username = username
        // Set the createGameRoomCalled to true
        createGameRoomCalled = true

        viewModelScope.launch {
            // // join the match through the apollo server
            gameApolloRepository.joinGame(
                username = username,
                gameMode = gameMode
            )
            // TODO: Everything that needs to be handled by the game start goes here
            gameID = gameApolloRepository.gameID
            // If the game id is not null than get the game updates
            if(gameID != null) {
                // Get the game, and observe the changes
                gameFirestoreRepository.getGame(
                    gameID = gameID!!,
                    gameMode = gameMode
                ).collect {
                    // Assign the game
                    game = it.data as Game?
                }
            }
        }
    }

    /**
     * Send a new message
     * @param content Content of the message
     */
    fun sendMessage(content: String = "HELLOOO") {
        viewModelScope.launch {
            // When sending a message username and game will never be null
            // because they are being sent from within the game
            if(username != null && game != null) {
                // Call the repository to sent the message
                gameFirestoreRepository.sendMessage(
                    username = username!!,
                    icon = profilePicture,
                    content = content,
                    gameID = gameID!!,
                    gameMode = game!!.gameMode
                )
            } else {
                Log.e("ERROR", "GameViewModel.SendMessage()")
            }
        }
    }


    /**
     * Remove user from the game
     */
    fun removeUserFromGame() {
        // Set the createGameRoom to false
        // This is needed for when the user minimizes the application
        createGameRoomCalled = false

        viewModelScope.launch {
            // Username is always going to have a value since
            // it's assigned when the game is being created
            if(username != null && game != null) {
                // Use the apollo server to remove the user
                gameApolloRepository.removeUser(
                    username = username!!,
                    gameMode = game!!.gameMode)
                //  TODO: Everything that needs to be handled by the game ends goes here
                // the game id inside the apollo repository is null reset all the values
                if(gameApolloRepository.gameID == null) {
                    // Set game to null
                    game = null
                    // Set game id to null
                    gameID = null
                }
            } else {
                // This should never occur
                Log.e("ERROR", "GameViewModel.removeUserFromGame()")
            }

        }
    }

    /**
     * Get a token from the user
     */
    fun getToken() {
        // if the token is not null
        if(gameApolloRepository.token == null) {
            // get a token
            gameApolloRepository.getUserAccessToken()
        }
    }
}