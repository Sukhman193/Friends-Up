package ca.finalfive.friendsup.composables.endgame

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.queuescreen.OffsetCard

/**
 * Description for the endgame screen
 * This is a static value, meaning that it will never change
 */
@Composable
fun EndGameDescription() {
    // Adding decorations to the screen
    Column {
        // Adding background decoration
        OffsetCard(
            modifier = Modifier
                .width(90.dp)
                .height(30.dp),
            containerModifier = Modifier
                .offset(y = 55.dp)
        ) {}

        // Offset Card for displaying description
        OffsetCard(
            modifier = Modifier
                .width(320.dp)
                .height(93.dp),
            containerModifier = Modifier
                .padding(top = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.game_ended_description),
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp),
                style = MaterialTheme.typography.caption,
            )
        }

        // Adding background decoration
        OffsetCard(
            modifier = Modifier
                .width(90.dp)
                .height(30.dp),
            containerModifier = Modifier
                .padding(start = 200.dp, top = 15.dp)
        ) {}
    }
}