package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.GameScreen
import ca.finalfive.friendsup.composables.QuestionOption

/**
 * The game screen for "Trivia"
 */
@Composable
fun TriviaGameScreen() {
    // Array of options for the user
    /* TODO: Make the options randomly generated depending on the question */
    val answers = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")

    GameScreen(
        gameTitle = R.string.game_trivia_title,
        gameType = R.string.game_trivia_type,
        titleFontSize = 60.sp) {
        // Question for the user
        Text(
            /* TODO: Make the question randomly generated along with the options */
            text = "Which one of these is a fruit?",
            color = Color.White,
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(horizontal = 30.dp)
        )

        // Space between the question and the question options
        Spacer(
            modifier = Modifier
                .padding(bottom = 30.dp)
        )

        // A for loop containing 4 button options
        for (answer in answers) {
            QuestionOption(option = answer)
        }
    }
}