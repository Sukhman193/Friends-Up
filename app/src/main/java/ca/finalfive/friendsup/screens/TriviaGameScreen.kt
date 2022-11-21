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
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * Screen for the trivia game
 * @param gameViewModel view model for the game controls
 */
@Composable
fun TriviaGameScreen(gameViewModel: GameViewModel) {
    // Game being played
    val game = gameViewModel.game!!
    // Index of the game being played
    val currentGameIndex = game.gameProgress
    // Question of the current trivia being played
    val question = game.gameContent[currentGameIndex].mainQuestion
    // Answers of the current trivia being played
    val answers = game.gameContent[currentGameIndex].questionOptions

    GameScreen(
        gameTitle = R.string.game_trivia_title,
        gameType = R.string.game_trivia_type,
        titleFontSize = 60.sp,
        gameViewModel = gameViewModel,
        gameTimer = 10f
    ) {
        // Question for the user
        Text(
            text = question,
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
            QuestionOption(option = answer, gameViewModel = gameViewModel)
        }
    }
}