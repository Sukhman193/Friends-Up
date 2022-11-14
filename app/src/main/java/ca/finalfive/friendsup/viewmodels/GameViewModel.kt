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
    private var username: String? by mutableStateOf(null)

    /**
     * Random user profile picture
     */
    private var profilePicture by mutableStateOf("https://picsum.photos/seed/${ UUID.randomUUID() }/200")

    /**
     * Game object for the gameplay
     */
    var game: Game? by mutableStateOf(null)

    /**
     * Helper function to check whether the game has been created
     */
    var createGameRoomCalled by mutableStateOf(false)

    /**
     * Boolean which refers to whether the report screen is open or not
     */
    var isReportScreenOpened by mutableStateOf(false)

    /**
     * Boolean which refers to whether the add as friend screen is open or not
     */
    var isAddAsFriendScreenOpened by mutableStateOf(false)

    /**
     * Apollo client repository
     */
    private val gameApolloRepository = GameApolloRepository.getInstance()

    /**
     * Game repository for getting firestore updates
     */
    private val gameFirestoreRepository = GameFirestoreRepository.getInstance()



    /**
     * Join a new game
     * @param username Username selected by the user
     * @param gameMode Game play mode selected by the user
     */
    fun joinGame(username: String = UUID.randomUUID().toString(), gameMode: String = GameMode.TRIVIA) {
        // Set the username for the user
        this.username = username

        this.game = null
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
     * @param popBackAScreen variable which defines whether the screen should pop back
     * once the game has ended or not. When a user decides to start a new game from the game
     * screen this value should be set to false
     */
    fun removeUserFromGame(popBackAScreen: Boolean = true) {
        // Set the createGameRoom to false
        // This is needed for when the user minimizes the application
        createGameRoomCalled = !popBackAScreen

        // Set the report screen open to false
        isReportScreenOpened = false
        // Set the add as friends queue screen to false
        isAddAsFriendScreenOpened = false

        // temporary value for game
        val tempGame = game

        // Set game to null
        game = null
        // Set game id to null
        gameID = null

        viewModelScope.launch {
            // Username is always going to have a value since
            // it's assigned when the game is being created
            if(username != null && tempGame != null) {
                // Use the apollo server to remove the user
                gameApolloRepository.removeUser(
                    username = username!!,
                    gameMode = tempGame.gameMode)
                //  TODO: Everything that needs to be handled by the game ends goes here

            } else {
                // This should never occur
                Log.e("ERROR", "GameViewModel.removeUserFromGame()")
            }
        }
    }

    /**
     * Report a user
     * @param reportReason Reasoning for the report
     */
    fun reportUser(reportReason: String) {

        // Set the create Room called to false
        createGameRoomCalled = false

        // Set the report screen open to false
        isReportScreenOpened = false

        // Create a temporary game and reset the game
        // for faster user interaction
        val tempGame = game
        game = null
        viewModelScope.launch {
            // Check if the game is not null
            // In this page the game is never going to be null
            if(tempGame != null) {
                gameApolloRepository.reportUser(
                    gameMode = tempGame.gameMode,
                    reportReason = reportReason
                )
            // Remove the user from the game
            removeUserFromGame()
            } else {
                Log.e("ERROR", "GameViewModel.reportUser()")
            }
        }
    }

    /**
     * End user's game when all the questions have ended
     * This function will route the users to the end Game Screen
     */
    fun endGame() {
        viewModelScope.launch {
            // Check if game is not null
            if(game != null) {
                // End the game
                gameApolloRepository.endGame(gameMode = game!!.gameMode)
            } else {
                Log.e("ERROR", "GameViewModel.endGame()")
            }
        }
    }

    /**
     * Update user friend list queue
     */
    fun updateUserFriendQueue() {
        viewModelScope.launch {
            // Set the add user screen to not the previous value
            isAddAsFriendScreenOpened = !isAddAsFriendScreenOpened
            // check if game is not null
            if(game != null) {
                // call update user friend queue
                gameApolloRepository.updateUserFriendQueue(game!!.gameMode)
            } else {
                Log.e("ERROR", "GameViewModel.updateUserFriendQueue()")
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