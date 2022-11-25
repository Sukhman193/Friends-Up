package ca.finalfive.friendsup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.finalfive.friendsup.composables.utils.BackgroundImage
import ca.finalfive.friendsup.factories.GameViewModelFactory
import ca.finalfive.friendsup.helpers.LockScreenOrientation
import ca.finalfive.friendsup.helpers.makeApplicationFullScreen
import ca.finalfive.friendsup.navigation.Navigation
import ca.finalfive.friendsup.ui.theme.StrangerCommonsTheme
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.delay

// https://blog.canopas.com/keyboard-handling-in-jetpack-compose-all-you-need-to-know-3e6fddd30d9a
//https://medium.com/@bhuvanesh_shan/making-full-screen-ui-in-android-jetpack-compose-46a7a1362b02

class MainActivity : ComponentActivity() {
    // Game view model for the application
    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Disable the keyboard effects
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Hide the top status bar
        actionBar?.hide()
        // Make the application full screen
        makeApplicationFullScreen(window)

        setContent {
            // Lock the screen orientation to portrait
            LockScreenOrientation()
            // initialize game view model
            gameViewModel = viewModel(factory = GameViewModelFactory())

            // Every time there is an error message, set the error message to be null
            LaunchedEffect(key1 = gameViewModel.errorMessage) {
                // This is required because if the same error occurs
                // Twice, there will be a toast for only one error
                delay(300)
                gameViewModel.errorMessage = null
            }
            StrangerCommonsTheme(false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        // Add the status bar padding
                        .statusBarsPadding()
                        // Add the navigation bar padding
                        .navigationBarsPadding(),
                    color = MaterialTheme.colors.background
                ) {
                    // Display any in game error messages
                    if (gameViewModel.errorMessage != null) {
                        // Get the error message
                        val errorMessage = gameViewModel.errorMessage!!
                        // Display the toast of the error message
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    // Background image
                    BackgroundImage()
                    Navigation(
                        gameViewModel = gameViewModel,
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
