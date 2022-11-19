package ca.finalfive.friendsup.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.ui.theme.titleBackgroundShade

/**
 * Screen title for the different pages
 * @param title value of the text to display
 * @param modifier Modifier for additional changes
 */
@Composable
fun ScreenTitle(title: String, modifier: Modifier = Modifier) {

    Box {
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 140.dp, top = 20.dp)
                .offset(3.dp, 3.dp),
            color = titleBackgroundShade)
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 140.dp, top = 20.dp),
            color = Color.White
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun preview() {
    ScreenTitle(title = "Chat Room")
}