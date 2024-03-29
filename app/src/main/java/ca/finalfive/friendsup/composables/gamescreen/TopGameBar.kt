package ca.finalfive.friendsup.composables.gamescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R

/**
 * This composable is the bar at the top of each game
 * @param gameTitle represents the title of the game that will be displayed at the top left
 * @param fontSize is the font size of the title (would you rather would be different)
 * @param setReportPopup state for whether the report popup should be open or not
 */
@Composable
fun TopGameBar(
    gameTitle: Int,
    fontSize: TextUnit = 60.sp,
    setReportPopup: (Boolean) -> Unit
) {

    // Background box behind "Trivia" and the Flag icon
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.purple_game_container))
    ) {
        // Trivia text on top
        Text(
            text = stringResource(id = gameTitle),
            style = MaterialTheme.typography.h1,
            color = Color.White,
            fontSize = fontSize,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
        )
        // Flag icon / Report button
        Icon(
            painter = painterResource(id = R.drawable.flag),
            contentDescription = "Report Button",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(55.dp)
                .padding(end = 30.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    setReportPopup(true)
                },
            tint = colorResource(id = R.color.white)
        )
    }
}