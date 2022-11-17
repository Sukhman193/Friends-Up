package ca.finalfive.friendsup.repositories

import android.accounts.NetworkErrorException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.JoinGameMutation
import ca.finalfive.friendsup.UpdateAddUserFriendMutation
import ca.finalfive.friendsup.ReportUserMutation
import ca.finalfive.friendsup.EndGameMutation
import ca.finalfive.friendsup.RemoveUserMutation
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/**
 * Repository for the game which let's you handle all the actions related to games
 * @param apolloClient client for the Apollo server
 */
class GameApolloRepository(private val apolloClient: ApolloClient) {

    /**
     * Game id of the currently joined game
     */
    var gameID: String? by mutableStateOf(null)
    /**
     * Token of the current user
     */
    private var token: String? by mutableStateOf(null)

    /**
     * Create match in the firestore database
     * @param username username of the user
     * @param gameMode gameMode selected by the user
     * @throws NetworkErrorException thrown if there is a network error like
     * no network connection
     * @throws ApolloException for any errors received by the apollo graphql server
     */
    suspend fun joinGame(username: String, gameMode: String) {
        // Get the token user token
        this.getUserAccessToken()
        // Create the apollo server request
        val apolloRequest =
            JoinGameMutation(
                memberName = username,
                gameMode = gameMode,
                userAccess = token!!
            )
        // Make the request to the endpoint
        val response = apolloClient.mutation(apolloRequest).execute()
        // check if there is any error
        if (!response.errors.isNullOrEmpty()) {
            // if there is an authentication error than call this function again but instead get a new token
            // If a user stays on the app for too long the token might expire
            if (response.errors?.get(0)?.message == "User token is invalid") {
                token = null
                this.joinGame(username, gameMode)
            } else {
                // If there is any other type of error, throw the content of the error message
                throw ApolloException(response.errors?.get(0)?.message.toString())
            }
            // end function
            return
        }
        // Assign the game id
        gameID = response.data?.joinGame?.id
    }

    /**
     * Remove user from the game
     * @param username Username of the user
     * @param gameMode Game mode selected for the game
     * @throws NetworkErrorException thrown if there is a network error like
     * no network connection
     * @throws ApolloException for any errors received by the apollo graphql server
     */
    suspend fun removeUser(username: String, gameMode: String) {
        // Get the token
        this.getUserAccessToken()
        // Check if there is a game id
        if(gameID != null) {
            // Create apollo server request
            val apolloRequest = RemoveUserMutation(
                memberName = username,
                gameMode = gameMode,
                access = token!!,
                gameId = gameID!!
            )
            // Make the request to the endpoint
            val response = apolloClient.mutation(apolloRequest).execute()
            // check if there is any error
            if (!response.errors.isNullOrEmpty()) {
                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if (response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.removeUser(username, gameMode)
                } else {
                    // If there is any other type of error, throw the content of the error message
                    throw ApolloException(response.errors?.get(0)?.message.toString())
                }
                // Return in case there is any type of errors
                return
            }
            // If the user is deleted successfully remove the user
            if(response.data?.removeUser?.success == true) {
                // Set game id to null
                gameID = null
            }
        }
    }

    /**
     * End the user's current game
     * @param gameMode game mode being played
     * @throws NetworkErrorException thrown if there is a network error like
     * no network connection
     * @throws ApolloException for any errors received by the apollo graphql server
     */
    suspend fun endGame(gameMode: String) {
        // Get a new token
        this.getUserAccessToken()
        // check if the game id is not null
        if(gameID != null) {
            // apollo request for the end game
            val apolloRequest = EndGameMutation(
                access = token!!,
                gameMode = gameMode,
                gameId = gameID!!
            )
            // Execute the apollo request
            val response = apolloClient.mutation(apolloRequest).execute()
            // Check if there are any errors
            if(response.errors != null) {
                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if (response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.endGame(gameMode)
                } else {
                    // throw any errors sent by the apollo server
                    throw ApolloException(response.errors?.get(0)?.message.toString())
                }
            }
        }
    }

    /**
     * Report user
     * @throws NetworkErrorException thrown if there is a network error like
     * no network connection
     * @throws ApolloException for any errors received by the apollo graphql server
     */
    suspend fun reportUser(gameMode: String, reportReason: String) {
        // Get a new token
        this.getUserAccessToken()
        // check if the the game id is not null
        if(gameID != null) {
            // apollo request for the end game
            val apolloRequest = ReportUserMutation(
                access = token!!,
                gameMode = gameMode,
                gameId = gameID!!,
                reportedReason = reportReason
            )
            // Execute the apollo request
            val response = apolloClient.mutation(apolloRequest).execute()
            if(response.errors != null) {
                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if (response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.reportUser(gameMode, reportReason)
                } else {
                    // throw any error received by the apollo server
                    throw ApolloException(response.errors?.get(0)?.message.toString())
                }
            }
        }
    }

    /**
     * Add or remove user from the friend list queue screen
     * In case the user is already in the queue, the user will be removed
     * In case the user is not in the queue, the user will be added
     * @param gameMode game mode of the current game
     * @throws NetworkErrorException thrown if there is a network error like
     * no network connection
     * @throws ApolloException for any errors received by the apollo graphql server
     */
    suspend fun updateUserFriendQueue(gameMode: String) {
        // Get the access token
        this.getUserAccessToken()
        // Check if the game id is not null
        if(gameID != null) {
            // apollo request for update friend queue list
            val apolloRequest = UpdateAddUserFriendMutation(
                access = token!!,
                gameId = gameID!!,
                gameMode = gameMode
            )
            // Execute the apollo request
            val response = apolloClient.mutation(apolloRequest).execute()
            // Check if there are any errors
            if(response.errors != null) {
                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if (response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.updateUserFriendQueue(gameMode)
                } else {
                    // throw any error received by the apollo server
                    throw ApolloException(response.errors?.get(0)?.message.toString())
                }
            }
        }
    }

    /**
     * Get the user's access token
     * @throws NetworkErrorException thrown if there is a network error like
     * no network connection
     */
    suspend fun getUserAccessToken(){
        if(token == null) {
            // Ask for a token
            try {
                val tokenRes = Firebase.auth.currentUser
                    ?.getIdToken(true)?.await()
                // assign the token
                token = tokenRes?.token
            } catch (error: FirebaseAuthInvalidUserException) {
                // If the token cannot be retrieved throw a new exception
                throw NetworkErrorException("Network error: Please restart the application")
            }
        }
    }

    companion object {
        /**
         * Singleton for the repository
         */
        private var INSTANCE: GameApolloRepository? = null
        /**
         * Get instance of the repository
         * @return instance of the game apollo repository
         */
        fun getInstance(): GameApolloRepository {
            // If instance is null create a new gameApolloRepository
            if (INSTANCE == null) {
                val apolloClient = ApolloClient.Builder()
                    // TODO: Change the url below
                    .serverUrl("https://t8gqkrufr2.execute-api.us-west-1.amazonaws.com/dev/graphql")
                    .build()
                INSTANCE = GameApolloRepository(apolloClient)
            }
            // return instance
            return INSTANCE!!
        }
    }
}