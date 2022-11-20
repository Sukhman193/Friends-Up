package ca.finalfive.friendsup.composables.queuescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.ui.theme.darkPurple
import ca.finalfive.friendsup.ui.theme.mediumPurple

/**
 * Card with offset
 * @param content Content of the card
 * @param modifier Modifier must contain the width and height of the card
 * @param containerModifier Modifier for the container
 */
@Composable
fun OffsetCard(
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    // Container
    Box(
        modifier = containerModifier
    ) {
        // Background card offset
        Card(
            shape = RoundedCornerShape(30.dp),
            backgroundColor = mediumPurple,
            modifier = modifier
                // Set offset of the card
                .offset(x = (-5).dp, y = 5.dp)
        ) {}
        // Main card to display the user
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(30.dp),
            backgroundColor = darkPurple
        ) {
            // Content of the card
            content()
        }
    }
}