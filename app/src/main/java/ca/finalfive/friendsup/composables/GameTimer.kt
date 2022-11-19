package ca.finalfive.friendsup.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.animations.TimerAnimation
import kotlinx.coroutines.delay

/** This component represents the game timer for the mini games, including details around the timer
 * @param totalTime The initial starting time for the timer
 * @param prompt The word before the question the user is on (Ex. "Question" 1 of 5)
 * @param currentQuestion The question the users are on
 * @param totalQuestions The total amount of questions in the lobby
 */
@Composable
fun GameTimer(totalTime: Float, prompt: Int, currentQuestion: Int, totalQuestions: Int) {
    // Number on the timer
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0f) {
            // Will update every 0.2s
            delay(200L)
            currentTime -= 0.2f
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()) {
        // This is a side effect which is run in a Coroutine Scope
        // which will subtract the currentTime by 1 every second
        ProgressBar(totalTime = totalTime)

        // Row containing the current question number and the timer animation
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .padding(horizontal = 30.dp)) {

            // Current question number
            Text(text = stringResource(id = prompt, currentQuestion, totalQuestions),
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 6.dp)
            )

            Spacer(modifier = Modifier
                .weight(1f))

            Box(contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(45.dp)) {
                // Start the circle timer animation
                TimerAnimation(totalTime)
                // Contains the timer number which is counting down on lines 36-41
                Text(text = (String.format("%.0f", currentTime)).replace("-", ""),
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 7.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}