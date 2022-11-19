package ca.finalfive.friendsup.composables.drawings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

/**
 * Line separator for the screen, it adds a small
 * padding to the top of the line
 * @param strokeWidth Width of the stroke, default to `7f`
 * @param lineSpacing Spacing between the decorative lines, default to `40f`
 */
@Composable
fun LineSeparator(
    strokeWidth: Float = 7f,
    lineSpacing: Float = 40f,
) {
    // Draw decorative line
    Canvas(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
        // Get the length of the starting line and the ending line
        val startLineLength = (size.width * 0.05).toFloat()

        // draw at the beginning
        drawLine(
            color = Color.White,
            start = Offset.Zero,
            end = Offset(startLineLength,0f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // middle line
        drawLine(
            color = Color.White,
            start = Offset(startLineLength + lineSpacing,0f),
            end = Offset( size.width - startLineLength - lineSpacing,0f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // draw at the end
        drawLine(
            color = Color.White,
            start = Offset( size.width - startLineLength,0f),
            end = Offset( size.width,0f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}