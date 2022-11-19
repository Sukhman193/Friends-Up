package ca.finalfive.friendsup.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.models.GameQuestionOption

/**
 * The buttons options for Trivia and Would you Rather
 * @param option represents the name of the option
 * @param onClick handler for the button click
 */
@Composable
fun QuestionOption(
    option: GameQuestionOption,
    onClick: () -> Unit
) {
    // The entire button size (with the darker color as the background)
    Box(
        // Darker part of the button
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color(R.color.dark_purple))
            .clickable { onClick() }
    ) {
        // Lighter color on the left of the button
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(Color(R.color.light_purple))
        )

        // Text inside the button
        Text(
            text = option.optionText,
            color = Color.White,
            style = MaterialTheme.typography.h3,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 65.dp)
                .padding(bottom = 10.dp)
        )
    }
    // Spacer between the options
    Spacer(
        modifier = Modifier
            .padding(bottom = 12.dp)
    )
}