package ca.finalfive.friendsup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.navigation.Navigation
import ca.finalfive.friendsup.ui.theme.StrangerCommonsTheme
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    // Game view model for the application
    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize game view model
        gameViewModel = GameViewModel()
        // creating an instance of authViewModel

        val authViewModel = AuthViewModel()
        setContent {
            // Every time there is an error message, set the error message to be null
            LaunchedEffect(key1 = gameViewModel.errorMessage) {
                // This is required because if the same error occurs
                // Twice, there will be a toast for only one error
                delay(300)
                gameViewModel.errorMessage = null
            }

            StrangerCommonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Display any in game error messages
                    if(gameViewModel.errorMessage != null) {
                        // Get the error message
                        val errorMessage = gameViewModel.errorMessage!!
                        // Display the toast of the error message
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    // Background image
                    BackgroundImage()
                    Navigation(
                        gameViewModel = gameViewModel,
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }

    /**
     * Override the on pause lifecycle activity
     */
    override fun onPause() {
        // Remove the user from the queue when they leave the screen
        gameViewModel.removeUserFromGame()
        // default on pause handler
        super.onPause()
    }
}