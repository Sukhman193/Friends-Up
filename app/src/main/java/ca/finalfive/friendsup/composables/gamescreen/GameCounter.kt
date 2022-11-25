package ca.finalfive.friendsup.composables.gamescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.viewmodels.GameViewModel


/**
 * The top of the game screen, it will keep track of the time and everything
 * @param gameTitle is the title at the Top bar
 * @param gameType is the variable beside the numbers in the timer animation
 *      for example "<Question> 1 of 5". gameType being the word Question
 * @param titleFontSize font size for the title
 * @param gameTimer timer of the game
 * @param setReportPopup State for setting the report popup
 */
@Composable
fun Counter(
    gameTitle: Int,
    titleFontSize: TextUnit,
    gameTimer: Float,
    gameType: Int,
    gameViewModel: GameViewModel,
    setReportPopup: (Boolean) -> Unit
) {
    Column {
        // Container at the top which includes the title of the mini-game
        TopGameBar(
            gameTitle = gameTitle,
            fontSize = titleFontSize,
            setReportPopup = setReportPopup
        )

        // Space between the 3 lines and the top bar
        Spacer(
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        // Component containing the timer animation along with the lines above it,
        // and the words to the left of the timer
        GameTimer(
            totalTime = gameTimer,
            prompt = gameType,
            gameViewModel = gameViewModel
        )
    }
}
