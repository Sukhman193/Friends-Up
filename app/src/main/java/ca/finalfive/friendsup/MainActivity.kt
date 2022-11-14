package ca.finalfive.friendsup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.navigation.Navigation
import ca.finalfive.friendsup.screens.GameQueueScreen
import ca.finalfive.friendsup.ui.theme.StrangerCommonsTheme
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.GameViewModel

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
            StrangerCommonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Background image
                    BackgroundImage()
//                    GameQueueScreen()
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
