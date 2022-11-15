package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.composables.ReportPopup
import ca.finalfive.friendsup.composables.ScreenTitle
import ca.finalfive.friendsup.composables.buttons.EndGameSubmitButton
import ca.finalfive.friendsup.composables.buttons.ReportButtonRed
import ca.finalfive.friendsup.composables.endgame.EndGameDescription
import ca.finalfive.friendsup.viewmodels.GameViewModel

@Composable
fun EndGameScreen(
    gameViewModel: GameViewModel
) {

    /**
     * Check whether the report popup should be open or not
     */
    val (reportPopupOpen, setReportPopupOpen)  = remember {
        mutableStateOf(false)
    }

    // Container of the screen
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Add background to the Screen
        BackgroundImage(showMoon = true)
        // Container of the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Add the screen title
            ScreenTitle(title = R.string.game_ended_title)

            // Container for aligning the content of the page dynamically
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp, top = 20.dp),
                // Arrange all the content of this container with flex spaced between
                verticalArrangement = Arrangement.SpaceBetween,
                // Align all the content to be horizontally aligned
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Add page description with decorations
                EndGameDescription()

                // Add as Friend button
                EndGameSubmitButton(
                    value = R.string.button_add_as_friend,
                    icon = R.drawable.add_friend,
                    // Set the button to enabled to false if the other player left the game
                    buttonEnabled = gameViewModel.game?.members?.size == gameViewModel.game?.maxMembers
                ) {
                    // Add user to friend queue
                    gameViewModel.updateUserFriendQueue()
                }

                // End game button
                EndGameSubmitButton(
                    value = R.string.button_add_start_new_game,
                    icon = R.drawable.start_new_game
                ) {
                    gameViewModel.removeUserFromGame(popBackAScreen = false)
                    // TODO: Add username to the join game
                    gameViewModel.joinGame()
                }

                // Adding some space for better ui Presentation
                Spacer(modifier = Modifier.height(10.dp))

                // Report button
                ReportButtonRed {
                    setReportPopupOpen(true)
                }
            }
        }
        // If reportPopupOpen is set to open than
        // display the report popup
        if(reportPopupOpen) {
            ReportPopup(
                setReportPopup = setReportPopupOpen,
                gameViewModel = gameViewModel)
        }
    }

}