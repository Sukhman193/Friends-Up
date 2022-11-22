package ca.finalfive.friendsup.viewmodels

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.friendsup.helpers.Error
import ca.finalfive.friendsup.models.Game
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.models.GameQuestionOption
import ca.finalfive.friendsup.repositories.GameApolloRepository
import ca.finalfive.friendsup.repositories.GameFirestoreRepository
import ca.finalfive.friendsup.services.ValidationService
import kotlinx.coroutines.launch
import java.util.*

/**
 * Game view model to control actions related to the game
 */
class GameViewModel : ViewModel() {
    /**
     * Id of the game to be joined
     */
    private var gameID: String? by mutableStateOf(null)

    /**
     * Saved username for the user
     * This is needed because if a game has 2 users
     * with the same username, one of them will be renamed
     */
    var savedUsername: String? by mutableStateOf(null)

    /**
     * Random user profile picture
     */
    private var profilePicture by mutableStateOf("https://picsum.photos/seed/${UUID.randomUUID()}/200")

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
     * Boolean which refers to whether a question has been answered or not
     */
    var questionAnswered by mutableStateOf(false)

    /**
     * Apollo client repository
     */
    private val gameApolloRepository = GameApolloRepository.getInstance()

    /**
     * Game repository for getting firestore updates
     */
    private val gameFirestoreRepository = GameFirestoreRepository.getInstance()

    /**
     * Game mode selected by the user
     */
    private var gameMode by mutableStateOf(GameMode.TRIVIA)

    /**
     * Error message to display the user
     */
    var errorMessage: String? by mutableStateOf(null)

    /**
     * Join a new game
     * @param gameMode Game play mode selected by the user
     */
    fun joinGame(gameMode: String = this.gameMode) {
        // Reassign the game mode in case the user changes it
        if (this.gameMode != gameMode) {
            this.gameMode = gameMode
        }

        // Set game to be null
        // Needed when the user clicks on play again in the end game screen
        this.game = null
        // Set the createGameRoomCalled to true
        this.createGameRoomCalled = true
        viewModelScope.launch {
            // // join the match through the apollo server
            try {
                // Make the repository request
                gameApolloRepository.joinGame(
                    gameMode = gameMode,
                )
                // Set the game id
                gameID = gameApolloRepository.gameID
                // Set the savedUsername
                savedUsername = gameApolloRepository.username
                // this will never happen because the gameApolloRepository
                // will throw an error beforehand
                // If the game id is null than throw an error
                if (gameID == null) {
                    throw Exception("Error connecting to the server")
                }
                // Get the game, and subscribe to the firestore changes the changes
                gameFirestoreRepository.getGame(
                    gameID = gameID!!,
                    gameMode = gameMode
                ).collect {
                    // Assign the game
                    game = it.data as Game?
                }

            } catch (error: Exception) {
                // In case of errors set the error message to be here
                errorMessage = error.message
            }
        }
    }

    /**
     * Handle sending a message to the user
     * @param content Content of the message
     */
    fun sendMessage(content: String, context: Context) {
        // Game cannot be null here
        if (game == null) {
            return
        }
        // Get the icon for the messages
        // So that it matches the user icon
        game!!.members.map {
            if (it.username == this.savedUsername) {
                this.profilePicture = it.icon
            }
        }
        // Get instance for the validation
        val validationInstance = ValidationService.getInstance()
        // Error check the the message
        try {
            // Validate the text
            validationInstance.isMessageValid(content)
        } catch (e: Error.ValidationException) {
            // In case of error, display the error to the user
            e.makeToast(context)
            // end the function
            return
        }

        // If no errors occurred send the message
        viewModelScope.launch {
            // When sending a message username and game will never be null
            // because they are being sent from within the game
            if (savedUsername == null || game == null) {
                return@launch
            }
            // Call the repository to sent the message
            gameFirestoreRepository.sendMessage(
                username = savedUsername!!,
                icon = profilePicture,
                content = content,
                gameID = gameID!!,
                gameMode = game!!.gameMode
            )
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
        // This is required for quick ui update
        // temporary value for game
        val tempGame = game
        // Set game to null
        game = null
        // Set game id to null
        gameID = null
        viewModelScope.launch {
            // Username is always going to have a value since
            // it's assigned when the game is being created
            if (savedUsername == null || tempGame == null) {
                return@launch
            }

            try {
                // Use the apollo server to remove the user
                gameApolloRepository.removeUser(
                    username = savedUsername!!,
                    gameMode = tempGame.gameMode
                )
            } catch (error: Exception) {
                // In case of errors set the error message to be here
                errorMessage = error.message
            }
        }
    }

    /**
     * Function for reporting a user
     * @param reportReason Reasoning for the report
     */
    fun reportUser(reportReason: String) {
        // Set the create Room called to false
        this.createGameRoomCalled = false
        // Set the report screen open to false
        this.isReportScreenOpened = false
        // Create a temporary game and reset the game
        // for faster user interaction
        val tempGame = game
        this.game = null
        viewModelScope.launch {
            // Check if the game is not null
            // In this page the game is never going to be null
            if (tempGame == null) return@launch

            try {
                // Make the apollo request
                gameApolloRepository.reportUser(
                    gameMode = tempGame.gameMode,
                    reportReason = reportReason
                )
            } catch (error: Exception) {
                // In case of errors set the error message to be here
                errorMessage = error.message
            }
            // Remove the user from the game
            removeUserFromGame()
        }
    }

    /**
     * End user's game when all the questions have ended
     * This function will route the users to the end Game Screen
     */
    private fun endGame() {
        // If game is null than exit the function
        if (game == null) return

        viewModelScope.launch {
            try {
                // end the game
                gameApolloRepository.endGame(gameMode = game!!.gameMode)
            } catch (error: NetworkErrorException) {
                // catch the thrown error
                errorMessage = error.message
            }
        }
    }

    /**
     * Update user friend list queue
     * This function is used to add the user to the friend list queue.
     * It also handles the cancel for adding the friend
     */
    fun updateUserFriendQueue() {
        // Set the add user screen to not the previous value
        this.isAddAsFriendScreenOpened = !this.isAddAsFriendScreenOpened
        viewModelScope.launch {
            // Game should never be null here
            if (game == null) {
                return@launch
            }
            try {
                // call update user friend queue
                gameApolloRepository.updateUserFriendQueue(game!!.gameMode)
            } catch (error: java.lang.Exception) {
                // In case of errors set the error message to be here
                errorMessage = error.message
            }

        }
    }

    /**
     * Handle the user selecting a game option answer
     * @param gameOption option of the game that the user selected
     */
    fun handleAnswerGameOption(gameOption: GameQuestionOption) {
        // Check if the question has already been answered
        if (!this.questionAnswered) {
            // Set the question answered variable to be true
            this.questionAnswered = true
            viewModelScope.launch {
                // When updating the game option the will never be null
                // because they cannot select an option without having the game
                if (savedUsername == null || game == null) {
                    return@launch
                }
                // Call the repository to update the answer
                gameFirestoreRepository.sendAnswerSelected(
                    username = savedUsername!!,
                    gameID = gameID!!,
                    gameMode = game!!.gameMode,
                    gameOption = gameOption
                )
            }
        }
    }

    /**
     * Handle game progress,
     * This will route to the next question
     */
    fun handleGameProgress() {
        // Game should never be null when this function is called
        if (game == null) return
        // Check if it's the last question that is being answered
        if (game!!.gameContent.size - 1 == game!!.gameProgress) {
            // end the game if the timer of the last question reaches 0
            this.endGame()
            return
        }
        // set the question answered to false
        this.questionAnswered = false
        viewModelScope.launch {
            // Update the game
            gameFirestoreRepository.handleGameProgress(
                gameProgress = game!!.gameProgress + 1,
                gameMode = gameMode,
                gameID = gameID!!
            )
        }
    }
}
