package ca.finalfive.friendsup.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.friendsup.models.Game
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.models.Resource
import ca.finalfive.friendsup.repositories.GameApolloRepository
import ca.finalfive.friendsup.repositories.GameFirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// Temporary View Model
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
                gameFirestoreRepository.getGame(
                    gameID = gameID!!,
                    gameMode = gameMode
                ).collect {
                    game = it.data as Game?
//                    gameFlow.value = it
//                    game = gameFlow.value?.data as Game?
                    Log.d("LLAMA_VIEWMODEL", game.toString())
                }
            }
        }
    }

    fun removeUserFromGame() {
        createGameRoomCalled = false
        viewModelScope.launch {
            // Username is always going to have a value since
            // it's assigned when the game is being created
            if(username != null && game != null) {
                gameApolloRepository.removeUser(
                    username = username!!,
                    gameMode = game!!.gameMode)
                // TODO: Everything that needs to be handled by the game ends goes here
                // the game id inside the apollo repository is null reset all the values
                if(gameApolloRepository.gameID == null) {
                    // Set game to null
                    game = null
                    // Set game id to null
                    gameID = null
                }
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