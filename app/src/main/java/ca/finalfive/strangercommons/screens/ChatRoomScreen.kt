package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.navigation.Route

@Composable
fun ChatRoomScreen(navController: NavController) {

    Column() {
        Text(text = "ChatRoom page")
        
        Button(onClick = { navController.navigate(Route.TriviaGameScreen.route) }) {
            Text(text = "Trivia")
        }

        Button(onClick = { navController.navigate(Route.PromptGameScreen.route) }) {
            Text(text = "Prompt")
        }
    }
}