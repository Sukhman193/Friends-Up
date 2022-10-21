package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
        /**
         * Navigation container, it will contain the bottom navigation, as well as the screen
         * @param navController Navigation controller for the application
         * @param screen Screen to be displayed in the application
         */
fun NavigationContainer(navController: NavController, screen: @Composable () -> Unit) {
    // Calling the Background Image to be displayed
    BackgroundImage(true)
    // The scaffold will contain the bottom navigation
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        // Container for the screen to be displayed
        Box(modifier = Modifier.padding(paddingValues)) {
            screen()
        }

    }
}