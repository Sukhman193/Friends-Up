package ca.finalfive.friendsup.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.ui.theme.titleBackgroundShade

@Composable
        /**
         * Screen title for the different pages
         * @param title value of the text to display
         * @param modifier Modifier for additional changes
         */
fun ScreenTitle(
    title: Int,
    modifier: Modifier = Modifier) {

    // Container of the screen
    Column {

        Box {
            // Display the background offset text
            ScreenTitleText(
                title = title,
                modifier = modifier.offset(4.dp, 4.dp),
                color = titleBackgroundShade)
            // Display the Screen title text
            ScreenTitleText(title = title, modifier = modifier)

        }
        // Draw and underline decoration
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 140.dp)) {
            // Get max width for the screen
            val maxWidth = 650f
            /**
             * Draw a horizontal white line with width of 5
             * @param start x value of the Offset for the line
             * @param end x value of the Offset for the line
             */
            fun myDrawLine(
                start: Float,
                end: Float
            ) {
                drawLine(
                    color = Color.White,
                    start = Offset(x = start, y = 0f),
                    end = Offset(x = end, y = 0f),
                    cap = StrokeCap.Round,
                    strokeWidth = 7f
                )
            }
            // Draw first line
            // this line is going to be 6% of the canvas width
            myDrawLine(
                start = 0f,
                end = maxWidth * 0.06f
            )
            // Draw second line
            // this line is going to be around 34% of the entire canvas width
            myDrawLine(
                start = maxWidth * 0.06f + 20f,
                end = maxWidth * 0.4f - 20f
            )
            // Draw second line
            // this line is going to cover the rest of the canvas
            myDrawLine(
                start = maxWidth * 0.4f,
                end = maxWidth
            )
        }
    }
}

@Composable
fun ScreenTitleText(
    title: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    // Offset text for the background
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.h1,
        modifier = modifier
            .padding(start = 140.dp, top = 20.dp),
        color = color,
        // set the line height
        lineHeight = 47.sp
    )
}


