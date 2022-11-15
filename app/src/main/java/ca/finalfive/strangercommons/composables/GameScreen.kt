package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.finalfive.strangercommons.viewmodels.currentQuestion
import ca.finalfive.strangercommons.viewmodels.totalQuestions

@Composable
        /**
         * GameScreen is the default game screen for all the different kinds of
         * games, which contains the Top bar, Game Timer animation and the Message Box
         * @param gameTitle is the title at the Top bar
         * @param gameType is the variable beside the numbers in the timer animation
         *      for example "<Question> 1 of 5". gameType being the word Question
         * @param isWYR checks to see if the game is Would You Rather so that it'll
         *      reduce the font for the title since it's such a long name
         */
fun GameScreen(gameTitle: Int, gameType: Int, isWYR: Boolean = false, gameContent: @Composable () -> Unit) {
    // Black background over the background to make the background dimmer
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5F))
    )

    // Column containing all the elements you currently see, this is used to
    // arrange the MessageBox to the bottom
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        // Column containing all the elements except the MessageBox
        Column(modifier = Modifier
            ) {
            // Container at the top which includes the title of the mini-game
            TopGameBar(gameTitle, isWYR)
            // Space between the 3 lines and the top bar
            Spacer(
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            // Component containing the timer animation along with the lines above it,
            // and the words to the left of the timer
            GameTimer(
                totalTime = 90f,
                prompt = gameType,
                currentQuestion = currentQuestion,
                totalQuestions = totalQuestions
            )

            // The content for which ever game is used, such as cards for
            // Cards Against Humanities, or Question Options for Trivia
            // and Would you Rather
            gameContent()

            // Spacer between the options
            Spacer(
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
        }
        Box(modifier = Modifier
            .padding(bottom = 12.dp)) {
            MessageBox()
        }
    }
}