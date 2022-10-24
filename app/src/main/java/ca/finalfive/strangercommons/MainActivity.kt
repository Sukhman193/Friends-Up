package ca.finalfive.strangercommons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ca.finalfive.strangercommons.composables.BackgroundImage
import ca.finalfive.strangercommons.navigation.Navigation
import ca.finalfive.strangercommons.ui.theme.StrangerCommonsTheme
import ca.finalfive.strangercommons.viewmodels.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MyViewModel()

        setContent {
            StrangerCommonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Background image
                    BackgroundImage()
                    Navigation(viewModel = viewModel)
                }
            }
        }
    }
}
