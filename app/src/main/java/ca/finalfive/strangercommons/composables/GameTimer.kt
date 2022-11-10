package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.animations.TimerAnimation
import ca.finalfive.strangercommons.ui.theme.comfortaa
import kotlinx.coroutines.delay

@Composable
        /** This component represents the game timer for the mini games, including details around the timer
         * @param totalTime The initial starting time for the timer
         * @param prompt The word before the question the user is on (Ex. "Question" 1 of 5)
         * @param currentQuestion The question the users are on
         * @param totalQuestions The total amount of questions in the lobby
         */
fun GameTimer(totalTime: Int, prompt: String, currentQuestion: Int, totalQuestions: Int) {
    // Timer Number
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    // This is a side effect which is run in a Coroutine Scope
    // which will subtract the currentTime by 1 every second
    LaunchedEffect(key1 = currentTime) {
        if (currentTime != 0) {
            delay(1000L)
            currentTime -= 1
        }
    }

    MaterialTheme(typography = Typography(defaultFontFamily = comfortaa)) {
        Box(modifier = Modifier
            .fillMaxWidth()) {
            // Image of the 3 lines at the top
            Image(
                painter = painterResource(id = R.drawable.line),
                contentDescription = "Line above timer",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .padding(horizontal = 30.dp),
                contentScale = ContentScale.FillBounds
            )
            // Row containing the current question number and the timer animation
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .padding(horizontal = 30.dp)) {

                // Current question number
                Text(text = "$prompt $currentQuestion of $totalQuestions",
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
                    TimerAnimation(totalTime.toFloat())
                    // Contains the timer number which is counting down on lines 36-41
                    Text(text = currentTime.toString(),
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
}