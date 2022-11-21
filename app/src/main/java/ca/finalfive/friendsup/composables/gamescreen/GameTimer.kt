package ca.finalfive.friendsup.composables.gamescreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.animations.TimerAnimation
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.delay

/** This component represents the game timer for the mini games, including details around the timer
 * @param totalTime The initial starting time for the timer
 * @param prompt The word before the question the user is on (Ex. "Question" 1 of 5)
 * @param gameViewModel view model  for the game
 */
@Composable
fun GameTimer(
    totalTime: Float,
    prompt: Int,
    gameViewModel: GameViewModel
) {
    // If game does not exist than return
    // there is a small instance when game is
    // null and the game is being used here
    // other error checking is performed
    // inside the navigation stack
    if (gameViewModel.game == null) {
        return
    }
    // Content of the game
    val game = gameViewModel.game!!
    // Keeps track of what question the user is on
    val currentQuestion = game.gameProgress
    // The total amount of questions in the game
    val totalQuestions = game.gameContent.size
    // Number on the timer
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    // Change the timer for the response
    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0f) {
            // Will update every 0.2s
            delay(200L)
            currentTime -= 0.2f
        } else {
            // check if the answers have been given
            // get the length of the options for the users
            val questionOptions = game.gameContent[game.gameProgress].questionOptions
            // Value which will count how many people answered the question
            var answeredQuestion = 0
            // Iterate over the question options to find the number of users who
            // have answered
            questionOptions.map {
                answeredQuestion += it.selectedBy.size
            }
            // if the game has options and not all the users responded, end the game
            if(answeredQuestion != game.members.size && questionOptions.isNotEmpty()) {
                // end the game
                gameViewModel.errorMessage = "Game ended for user inactivity"
            }
            // allow only one user to progress
            if(gameViewModel.savedUsername != game.members[0].username) {
                return@LaunchedEffect
            }
            // Go to the next question
            gameViewModel.handleGameProgress()
        }
    }

    // Every time the question changes reset the timer
    LaunchedEffect(key1 = currentQuestion) {
        currentTime = totalTime
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // This is a side effect which is run in a Coroutine Scope
        // which will subtract the currentTime by 1 every second
        ProgressBar(totalTime = totalTime, updateValue = currentQuestion)

        // Row containing the current question number and the timer animation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .padding(horizontal = 30.dp)
        ) {

            // Current question number
            Text(
                text = stringResource(id = prompt, currentQuestion + 1, totalQuestions),
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 6.dp)
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(45.dp)
            ) {
                // Start the circle timer animation
                if(currentTime < totalTime) {
                    // Start the circle timer animation
                    TimerAnimation(totalTime - 1f)
                }
                // Contains the timer number which is counting down on lines 36-41
                Text(
                    text = (String.format("%.0f", currentTime)).replace("-", ""),
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 7.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}