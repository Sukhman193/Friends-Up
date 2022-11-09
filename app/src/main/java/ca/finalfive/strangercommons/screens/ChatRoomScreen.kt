package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.GameTimer
import ca.finalfive.strangercommons.composables.showMoon
import ca.finalfive.strangercommons.navigation.Route

@Composable
fun ChatRoomScreen(navController: NavController) {
    showMoon = true

    Column() {
        Text(text = "ChatRoom page")
        
        Button(onClick = { navController.navigate(Route.TriviaGameScreen.route) }) {
            Text(text = "Button")
        }
    }
}