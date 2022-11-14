package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.finalfive.strangercommons.R

// The colors used for the gradient in the button
val lightBlue = Color(0xFF5275CF)
val lightPurple = Color(0xFF6B66DC)

@Preview
@Composable
        /**
         * This component is for the send button in the MessageBox composable
         */
fun SendButton() {
    // Box with the gradient color inside
    Box(modifier = Modifier
        .size(55.dp)
        .clip(RoundedCornerShape(5.dp))
        .clickable {/* TODO */ }
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    lightPurple,
                    lightBlue
                ),
                start = Offset(0.0f, 35.0f),
                end = Offset(35.0f, 0.0f)
            )
        )) {
        // Image of the paper airplane in the middle of the button
        Image(
            painter = painterResource(id = R.drawable.airplane),
            contentDescription = "Airplane icon",
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.Center)
        )
    }
}