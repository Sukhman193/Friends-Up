package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ca.finalfive.friendsup.R

/**
 * This component represents the background image
 * @param showMoon boolean that represents whether the moon is
 * displayed or not
 */
@Composable
fun BackgroundImage(showMoon: Boolean = false, darken: Boolean = false) {
    val image = if (showMoon) {
        // If moon is being shown
        painterResource(id = R.drawable.backgroundwithmoon)
    } else {
        // If moon isn't being shown
        painterResource(id = R.drawable.background)
    }

    /**
     * Opacity of the image to delete
     */
    val backgroundAlpha = if (darken) {
        0.7f
    } else {
        1.0f
    }

    // Black background color
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black))
    // Display background image that fills the entire screen
    Image(
        painter = image,
        contentDescription = "Background Image",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds,
        alpha = backgroundAlpha
    )
}