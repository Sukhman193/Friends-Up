package ca.finalfive.friendsup.repositories
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.*
import com.apollographql.apollo3.ApolloClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class GameApolloRepository(private val apolloClient: ApolloClient) {

    /**
     * Game id of the currently joined game
     */
    var gameID: String? by mutableStateOf(null)
    /**
     * Token of the current user
     */
    var token: String? by mutableStateOf(null)

    /**
     * Create match in the firestore database
     * @param username username of the user
     * @param gameMode gameMode selected by the user
     */
    suspend fun joinGame(username: String, gameMode: String) {
        // check if the token exists
        if(token == null) {
            // if the token is null than retrieve the user token
            this.getUserAccessToken()
        }
        // Check if the token is not null or an empty string
        if(token != null || token != "") {
            // Create the apollo server request
            val apolloRequest =
                JoinGameMutation(
                    memberName = username,
                    gameMode = gameMode,
                    userAccess = token!!)

            try {
                // Make the request to the endpoint
                val response = apolloClient.mutation(apolloRequest).execute()

                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if(response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.joinGame(username, gameMode)
                }
                // Get the game id
                gameID = response.data?.joinGame?.id
            } catch(e: java.lang.Exception) {
                Log.e("ERROR", "GameApolloRepository.joinGame()")
            }
        }
    }

    /**
     * Remove user from the game
     * @param username Username of the user
     * @param gameMode Game mode selected for the game
     */
    suspend fun removeUser(username: String, gameMode: String) {
        // if token is null get the token
        if(token!=null) {
            this.getUserAccessToken()
        }

        // Check if the token is not null and the id is not null
        // Since removing a user from the game will never happen
        // If the game is not created they both should never be null
        // Token can be null if it expires before the game ends
        if(token != null && gameID != null) {
            // Create apollo server request
            val apolloRequest = RemoveUserMutation(
                memberName = username,
                gameMode = gameMode,
                access = token!!,
                gameId = gameID!!
            )
            try {
                // Make the request to the endpoint
                val response = apolloClient.mutation(apolloRequest).execute()

                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if(response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.removeUser(username, gameMode)
                }
                // If the user is deleted successfully remove the user
                if(response.data?.removeUser?.success == true) {
                    // Set game id to null
                    gameID = null
                }
            } catch(e: java.lang.Exception) {
                Log.e("ERROR", "GameApolloRepository.removeUser()")
            }
        }
    }

    /**
     *  End the user's current game
     *  @param gameMode game mode being played
     */
    suspend fun endGame(gameMode: String) {
        // Check if the token is not null
        if(this.token == null) {
            // Get a new token
            this.getUserAccessToken()
        }

        // check if the token and the game id are not null
        if(token != null && gameID != null) {
            // apollo request for the end game
            val apolloRequest = EndGameMutation(
                access = token!!,
                gameMode = gameMode,
                gameId = gameID!!
            )

            try {
                // Execute the apollo request
                val response = apolloClient.mutation(apolloRequest).execute()

                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if(response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.endGame(gameMode)
                }
            } catch(e: java.lang.Exception) {
                Log.e("ERROR", "GameApolloRepository.endGame()")
            }
        }
    }

    /**
     * Report user
     */
    suspend fun reportUser(gameMode: String, reportReason: String) {
        // Check if the token is not null
        if(this.token == null) {
            // Get a new token
            this.getUserAccessToken()
        }

        // check if the token and the game id are not null
        if(token != null && gameID != null) {
            // apollo request for the end game
            val apolloRequest = ReportUserMutation(
                access = token!!,
                gameMode = gameMode,
                gameId = gameID!!,
                reportedReason = reportReason
            )

            try {
                // Execute the apollo request
                val response = apolloClient.mutation(apolloRequest).execute()

                // if there is an authentication error than call this function again but instead get a new token
                // If a user stays on the app for too long the token might expire
                if(response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.reportUser(gameMode, reportReason)
                }
            } catch(e: java.lang.Exception) {
                Log.e("ERROR", "GameApolloRepository.reportUser()")
            }
        }
    }

    /**
     * Add or remove user from the friend list queue screen
     * In case the user is already in the queue, the user will be removed
     * In case the user is not in the queue, the user will be added
     * @param gameMode game mode of the current game
     */
    suspend fun updateUserFriendQueue(gameMode: String) {
        // check if the token is available otherwise get a token
        if(token == null) {
            this.getUserAccessToken()
        }

        // Check if the token is not null and the game id is not null
        if(token != null && gameID != null) {
            // apollo request for update friend queue list
            val apolloRequest = UpdateAddUserFriendMutation(
                access = token!!,
                gameId = gameID!!,
                gameMode = gameMode
            )

            try {
                // Execute the apollo request
                val response = apolloClient.mutation(apolloRequest).execute()

//                // if there is an authentication error than call this function again but instead get a new token
//                // If a user stays on the app for too long the token might expire
                if(response.errors?.get(0)?.message == "User token is invalid") {
                    token = null
                    this.updateUserFriendQueue(gameMode)
                }
            } catch(e: java.lang.Exception) {
                Log.e("ERROR", "GameApolloRepository.updateUserFriendQueue()")
            }
        }
    }

    /**
     * Get the user's access token
     */
    fun getUserAccessToken(){
        if(token == null) {
            Firebase.auth.currentUser
                ?.getIdToken(true)
                ?.addOnSuccessListener {
                    token = it.token!!
                }
        }
        Log.d("LLAMAaaaha", token.toString())
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
                    .serverUrl("https://t8gqkrufr2.execute-api.us-west-1.amazonaws.com/dev/graphql")
                    .build()
                INSTANCE = GameApolloRepository(apolloClient)
                // Get the token every time the user is already authenticated
                INSTANCE!!.getUserAccessToken()
            }
            // return instance
            return INSTANCE!!
        }

    }

}