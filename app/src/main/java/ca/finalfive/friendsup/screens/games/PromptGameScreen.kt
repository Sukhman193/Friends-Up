package ca.finalfive.friendsup.screens.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.composables.gamescreen.GameScreen
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * The game screen for "Prompt"
 */
@Composable
fun PromptGameScreen(gameViewModel: GameViewModel) {

    // Timer for the game
    var gameTimer by remember {
        mutableStateOf(20f)
    }

    // Game being played
    val game = gameViewModel.game!!
    // Index of the game being played
    val currentPromptIndex = game.gameProgress
    // Prompt of the current game being played
    val prompt = game.gameContent[currentPromptIndex].mainQuestion

    LaunchedEffect(key1 = prompt) {
        gameTimer = 20f
    }
    // Game content which includes the topBar, gameTimer and the question
    // if there is one
    GameScreen(
        gameTitle = R.string.game_prompt_title,
        gameType = R.string.game_prompt_type,
        titleFontSize = 60.sp,
        gameTimer = gameTimer,
        gameViewModel = gameViewModel
    ) {
        // The only purpose of this box is to make a bottom alignment
        // for the skip button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            // Question for the user
            Text(
                text = prompt,
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
            )

            // check if it's the last question or not
            // if it's the last question don't display the button
            if(currentPromptIndex < 4) {
                // Skip button
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(45.dp)
                        .width(120.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(R.color.light_grey))
                        .align(Alignment.End)
                        .clickable {
                            gameViewModel.handleGameProgress()
                        }
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
}