package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.GameScreen
import ca.finalfive.strangercommons.composables.TriviaOption

@Composable
fun TriviaGameScreen(navController: NavController) {
    // Array of options for the user
    val answers = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")

    GameScreen() {
        // Question for the user
        // Displayed Question
        Text(
            text = "Which one of these is a fruit?",
            color = Color.White,
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(horizontal = 30.dp)
        )

        Spacer(
            modifier = Modifier
                .padding(bottom = 30.dp)
        )

        // A for loop containing 4 button options
        for (answer in answers) {
            TriviaOption(answer = answer)
        }
    }
}