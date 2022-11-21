package ca.finalfive.friendsup.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R
import kotlinx.coroutines.delay

/**
 * The progress bar above the amount of questions left and the timer animation
 * @param totalTime is the total amount of time for the progressBar
 */
@Composable
fun ProgressBar(totalTime: Float, updateValue: Int) {
    // Color for the progress bar
    val progressColor = colorResource(id = R.color.light_purple)
    // The progress, color and a boolean of whether it's complete or not
    // for the first bar
    var bar1Progress by remember {
        mutableStateOf(1f)
    }
    // The color of the first bar, which will be changed to transparent when it's done
    var bar1Color by remember {
        mutableStateOf(progressColor)
    }
    // Checks to see if the first bar is done
    var bar1Done by remember {
        mutableStateOf(false)
    }
    // The progress, color and a boolean for the second bar
    var bar2Progress by remember {
        mutableStateOf(1f)
    }
    // The color of the second bar, which will be changed to transparent when it's done
    var bar2Color by remember {
        mutableStateOf(progressColor)
    }
    // Checks to see if the second bar is done
    var bar2Done by remember {
        mutableStateOf(false)
    }
    // The progress, color and a boolean for the third and longest bar
    var bar3Progress by remember {
        mutableStateOf(1f)
    }
    // The color of the third bar, which will be changed to transparent when it's done
    var bar3Color by remember {
        mutableStateOf(progressColor)
    }
    // Checks to see if the third bar is done
    var bar3Done by remember {
        mutableStateOf(false)
    }

    // The value for the denominator in finding the decimal value for
    // a single bar
    var offsetValue by remember {
        mutableStateOf(1f)
    }
    // Current amount of time left
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    // A LaunchedEffect for the progress bar, it will run as long as currentTime is updating
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

    // every time the update value changes reset the progress bar
    LaunchedEffect(key1 = updateValue) {
        // reset the timer
        currentTime = totalTime
        // reset the values for bars being done
        bar2Done = false
        bar3Done = false
        // Reset all the progress of the bars
        bar1Progress = 1f
        bar2Progress = 1f
        bar3Progress = 1f
        // Reset the colors
        bar1Color = progressColor
        bar2Color = progressColor
        bar3Color = progressColor
    }

    // A canvas containing all the lines
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
}
