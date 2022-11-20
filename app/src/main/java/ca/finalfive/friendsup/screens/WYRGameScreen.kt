package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.GameScreen
import ca.finalfive.friendsup.composables.QuestionOption

/**
 * The game screen for "Would You Rather"
 */
@Composable
fun WYRGameScreen(navController: NavController) {
    // Array of options for the user
    /* TODO: Make the options randomly generated */
    val options = arrayOf("Option 1", "Option 2")

    // Game content which includes the topBar, gameTimer and
    // the question if there is one
    GameScreen(
        gameTitle = R.string.game_would_you_rather_title,
        gameType = R.string.game_would_you_rather_type,
        titleFontSize = 40.sp) {
            // Question for the user
            Text(
                text = "Would you rather...",
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )

            Spacer(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )

            // A for loop containing 4 button options
            for (option in options) {
                QuestionOption(option = option)
            }
        }
    }
