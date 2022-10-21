package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ca.finalfive.strangercommons.R

@Preview
@Composable
fun BackgroundImage(showMoon: Boolean = true) {
    val image = if (showMoon) {
        // If showMoon == true...
        painterResource(id = R.drawable.backgroundwithmoon)
    } else {
        // If showMoon == false
        painterResource(id = R.drawable.background)
    }

    Image(
        // Display background image here (depending on
        // whether showMoon is true or false
        painter = image,
        contentDescription = "Background Image",
        modifier = Modifier
            .fillMaxSize()
    )
}