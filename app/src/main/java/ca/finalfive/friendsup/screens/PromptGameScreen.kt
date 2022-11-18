package ca.finalfive.friendsup.screens

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
import ca.finalfive.friendsup.composables.GameScreen
import ca.finalfive.friendsup.R

@Composable
fun PromptGameScreen() {
    // Game content which includes the topBar, gameTimer and the question
    // if there is one
    GameScreen(
        gameTitle = R.string.game_prompt_title,
        gameType = R.string.game_prompt_type,
        titleFontSize = 60.sp) {
        // The only purpose of this box is to make a bottom alignment
        // for the skip button
        Box(
            modifier = Modifier
                .height(130.dp)
        ) {
            // Question for the user
            Text(
                text = "Why is the Earth not flat?",
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )

            // Skip button
            Box(
                modifier = Modifier
                    .padding(end = 30.dp)
                    .height(45.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(R.color.light_grey))
                    .align(Alignment.BottomEnd)
                    .clickable { /* TODO */ }
            ) {
                // Text inside the button
                Text(
                    text = "Skip >",
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontSize = 19.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 5.dp)
                )

            }
        }
    }
}