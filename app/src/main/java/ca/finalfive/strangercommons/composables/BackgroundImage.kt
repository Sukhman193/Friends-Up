package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ca.finalfive.strangercommons.R

@Composable
        /**
         * This component represents the background image
         * @param showMoon boolean that represents whether the moon is
         * displayed or not
         */
fun BackgroundImage(showMoon: Boolean = true) {
    val image = if (showMoon) {
        // If moon is being shown
        painterResource(id = R.drawable.backgroundwithmoon)
    } else {
        // If moon isn't being shown
        painterResource(id = R.drawable.background)
    }

    // Display background image that fills the entire screen
    Image(
        painter = image,
        contentDescription = "Background Image",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}