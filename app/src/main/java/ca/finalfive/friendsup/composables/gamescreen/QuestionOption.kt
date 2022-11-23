package ca.finalfive.friendsup.composables.gamescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.animations.ButtonAnimation
import ca.finalfive.friendsup.composables.drawings.GameAnswerBackground
import ca.finalfive.friendsup.composables.utils.ProfileIcon
import ca.finalfive.friendsup.models.GameQuestionOption
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.delay

/**
 * The buttons options for Trivia and Would you Rather
 * @param option represents the name of the option
 * @param gameViewModel view model of the game
 * @param correctAnswer The correct answer if there is an answer
 * @param displayCorrectAnswer States when to display the correct answer
 */
@Composable
fun QuestionOption(
    option: GameQuestionOption,
    gameViewModel: GameViewModel,
    correctAnswer: GameQuestionOption? = null,
    displayCorrectAnswer: Boolean = false
) {
    // If the game is null than don't display anything
    // The rest will be handled by the navigation bar
    if (gameViewModel.game == null) {
        return
    }
    // Height of the container
    // In case the text overflows this will be changed
    var containerHeight by remember {
        mutableStateOf(70.dp)
    }
    //Elevation of container
    //we will increase the
    var containerElevation by remember {
        mutableStateOf(0.dp)
    }
    // States whether an option has been clicked
    // this will allow the animation to start
    var isClicked by remember {
        mutableStateOf(false)
    }
    // States whether the option has been tapped once
    //this will let us display the message to double tap to confirm option
    var isTapped by remember {
        mutableStateOf(false)
    }
    // Get the game
    val game = gameViewModel.game!!
    // Every time the game progress changes (goes to the next question)
    // Set the is Clicked to false
    // Set the value of currentOption to option
    LaunchedEffect(key1 = game.gameProgress) {
        gameViewModel.questionAnswered = false
        isClicked = false
        // reset the current size when the game ends
        // We don't want to run this when the game first starts
        if (game.gameProgress > 0) {
            containerHeight = 70.dp
        }
    }

    //a composable message that will appear at the bottom of the tapped option
    //reference: https://stackoverflow.com/questions/73333287/how-to-show-a-composable-just-for-e-few-seconds
    @Composable
    fun timedTapMessage(){
        LaunchedEffect(key1 = {isTapped = true}){
            //delaying this action by 8 seconds allowing the message to show
            delay(8000)
            isTapped = false
        }
        //changing the elevation to give more of a shadow
        containerElevation = 6.dp
        //displaying the onTap message
        Row(){
            Text(
                color = colorResource(id = R.color.grey_font),
                fontSize = 6.sp,
                text = "Double tap to confirm option selection!"
            )
        }
    }

    // Every time the current option is changed
    // This will be affected by both users change
    // set is clicked to false if the button is not pressed
    // set is clicked to true if the button is pressed
    // Set the value of currentOption to option
    LaunchedEffect(key1 = option) {
        isClicked = option.selectedBy.isNotEmpty()
    }
    // Current context of the application
    val context = LocalContext.current
    // The entire button size (with the darker color as the background)
    Box(
        // Darker part of the button
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(containerHeight)
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                gameViewModel.handleAnswerGameOption(option, context)
            }
            .shadow(elevation = containerElevation)
            .pointerInput(option) {
                detectTapGestures(
                    onTap = {
                        isTapped = true
                    },
                    onDoubleTap = {
                        // Handle the option being selected
                        gameViewModel.handleAnswerGameOption(
                            gameOption = option,
                            context = context
                        )
                        //user doesn't need the message to double tap
                        isTapped = false
                    }
                )
            }
            .background(colorResource(R.color.dark_purple)),

    ) {
        // Lighter color on the left of the button
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(colorResource(R.color.light_purple))
        )
        // Check if there should be an animation
        // if yes, display the animation
        if (isClicked) {
            ButtonAnimation()
        }

        // If all the users have responded
        // then display the correct answer
        if (displayCorrectAnswer) {
            // Background for the game answer
            // It will fill the container with the
            // color desired
            GameAnswerBackground(
                option = option,
                // if display correct answer
                // is true than there must be a
                // correct answer
                correctAnswer = correctAnswer!!
            )
        }

            // Container of the option card
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Text inside the button
            Text(
                text = option.optionText,
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(start = 65.dp)
                    .padding(bottom = 10.dp),
                onTextLayout = {
                    if (it.didOverflowHeight) {
                        containerHeight = it.size.height.dp - containerHeight
                    }
                }
            )
            //the on tap message composable which will display for 8 seconds
            if (isTapped) {
                timedTapMessage()
            }

            // Display image of user who selected this option
            Row {
                // Iterate over all options of the current game
                option.selectedBy.forEach { selectedBy ->
                    // Iterate over all the members
                    game.members.forEach {
                        // display the icon of the member who matches
                        if (it.username == selectedBy) {
                            ProfileIcon(imageUrl = it.icon)
                        }
                    }
                }
            }
        }
    }
    // Spacer between the options
    Spacer(
        modifier = Modifier
            .padding(bottom = 12.dp)
    )
}