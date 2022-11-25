package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

/**
 * Display the profile icons on the game
 * @param imageUrl url for the image to display
 */
@Composable
fun ProfileIcon(imageUrl: String) {
    // image of the user
    Card(
        shape = CircleShape,
        modifier = Modifier.padding(5.dp).width(45.dp).height(45.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                contentScale = ContentScale.Crop,
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}