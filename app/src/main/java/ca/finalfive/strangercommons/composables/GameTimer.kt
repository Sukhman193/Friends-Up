package ca.finalfive.strangercommons.composables

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.strangercommons.animations.TimerAnimation
import ca.finalfive.strangercommons.viewmodels.*
import kotlinx.coroutines.delay

@Composable
        /** This component represents the game timer for the mini games, including details around the timer
         * @param totalTime The initial starting time for the timer
         * @param prompt The word before the question the user is on (Ex. "Question" 1 of 5)
         * @param currentQuestion The question the users are on
         * @param totalQuestions The total amount of questions in the lobby
         */
fun GameTimer(totalTime: Float, prompt: Int, currentQuestion: Int, totalQuestions: Int) {
    // Timer Number
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    // The progress, color and a boolean of whether it's complete or not
    // for the first bar
    var bar1Progress: Float by remember {
        mutableStateOf(1f)
    }
    var bar1Color: Color by remember {
        mutableStateOf(buttonColorLight)
    }
    var bar1Done: Boolean by remember {
        mutableStateOf(false)
    }
    // The progress, color and a boolean for the second bar
    var bar2Progress: Float by remember {
        mutableStateOf(1f)
    }
    var bar2Color: Color by remember {
        mutableStateOf(buttonColorLight)
    }
    var bar2Done: Boolean by remember {
        mutableStateOf(false)
    }
    // The progress, color and a boolean for the third and longest bar
    var bar3Progress: Float by remember {
        mutableStateOf(1f)
    }
    var bar3Color: Color by remember {
        mutableStateOf(buttonColorLight)
    }
    var bar3Done: Boolean by remember {
        mutableStateOf(false)
    }


    // This is a side effect which is run in a Coroutine Scope
    // which will subtract the currentTime by 1 every second
    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0f) {
            // The percentage (in decimals) to track how much is left
            val currentTimeDecimalValue = currentTime / totalTime

            // Will update every 0.2s
            delay(200L)
            currentTime -= 0.2f

            // 900f represents the end point of the 3rd bar
            // 340f represents the starting point of the 1st bar
            if (900f * bar3Progress >= 340f) {
                bar3Progress = currentTimeDecimalValue
            } // If 'The end point' * bar3Progress <= 'The starting point'
            if (900 * bar3Progress <= 340f && !bar3Done) {
                bar3Done = true
                // This will set the denominator for the next upcoming bar
                offsetValue = currentTimeDecimalValue
            }

            // 300f represents the end point of the 2rd bar
            // 60f represents the starting point of the 1st bar
            if (300f * bar2Progress >= 60f && bar3Done) {
                bar3Color = Color.Transparent
                // This line would look something like 0.36 / 0.36 at the start of the line
                bar2Progress = (currentTimeDecimalValue / offsetValue)
            } // If 'The end point' * bar2Progress <= 'The starting point'
            if (300f * bar2Progress <= 60f && !bar2Done){
                bar2Done = true
                offsetValue = currentTimeDecimalValue
            }

            // 300f represents the end point of the 2rd bar
            // 60f represents the starting point of the 1st bar
            if (20f * bar1Progress >= 0f && bar2Done) {
                bar2Color = Color.Transparent
                // This line would look something like 0.07 / 0.07 at the start of the line
                bar1Progress = (currentTimeDecimalValue / offsetValue)
            }

            // If the time on the timer is = 0
            if (currentTime <= 0f) {
                bar1Done = true
                bar1Color = Color.Transparent
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .padding(vertical = 5.dp, horizontal = 32.dp)
        ) {
            // 20f + 240f + 560f = 820f
            // The first line which starts at 0f and ends at 20f (White)
            drawLine(
                color = Color.White,
                start = Offset.Zero,
                end = Offset(20f, 0f),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
            // The second line which starts at 60f and ends at 300f (White)
            drawLine(
                color = Color.White,
                start = Offset(60f, 0f),
                end = Offset(300f, 0f),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
            // The third line which starts at 340f and ends at 900f (White)
            drawLine(
                color = Color.White,
                start = Offset(340f, 0f),
                end = Offset(900f, 0f),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )

            // The first line which starts at 0f and ends at 20f (Purple)
            // The end point will be multiplied with a decimal point similar to
            // a percentage to shrink it down. Once it's equal to the start point. It'll end
            drawLine(
                color = bar1Color,
                start = Offset.Zero,
                end = Offset(20f * bar1Progress, 0f),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
            // The second line which starts at 60f and ends at 300f (Purple)
            // The end point will be multiplied with a decimal point similar to
            // a percentage to shrink it down. Once the 'end point' = the 'start point'.
            // it'll stop moving the 2nd line and start moving the 1st line
            drawLine(
                color = bar2Color,
                start = Offset(60f, 0f),
                end = Offset(300f * bar2Progress, 0f),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
            // The third line which starts at 340f and ends at 900f (Purple)
            // The end point will be multiplied with a decimal point similar to
            // a percentage to shrink it down. Once the 'end point' = the 'start point'.
            // it'll stop moving the 3rd line and start moving the 2nd line
            drawLine(
                color = bar3Color,
                start = Offset(340f, 0f),
                end = Offset(900f * bar3Progress, 0f),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
        }

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