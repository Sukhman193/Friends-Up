package ca.finalfive.strangercommons.composables

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import ca.finalfive.strangercommons.viewmodels.*
import kotlinx.coroutines.delay

@Composable
fun ProgressBar(totalTime: Float) {
    // Current amount of time left
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0f) {
            // Will update every 0.2s
            delay(200L)
            currentTime -= 0.2f

            // The percentage (in decimals) to track how much is left
            val currentTimeDecimalValue = currentTime / totalTime

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
}