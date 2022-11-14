package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.finalfive.strangercommons.viewmodels.currentQuestion
import ca.finalfive.strangercommons.viewmodels.totalQuestions
import ca.finalfive.strangercommons.R

@Composable
fun GameScreen(gameContent: @Composable () -> Unit) {
// Black background on top to make the main background dimmer
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5F))
    )


    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        // Column containing all the elements you currently see
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Container at the top which includes the title of the mini-game
            TopGameBar(R.string.game_trivia_title)
            // Space between the 3 lines and the top bar
            Spacer(
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            // Component containing the timer animation along with the lines above it,
            // and the words to the left of the timer
            GameTimer(
                totalTime = 20f,
                prompt = R.string.trivia_game_prompt,
                currentQuestion = currentQuestion,
                totalQuestions = totalQuestions
            )

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