package ca.finalfive.friendsup.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.animations.ButtonAnimation
import ca.finalfive.friendsup.composables.utils.ProfileIcon
import ca.finalfive.friendsup.models.GameQuestionOption
import ca.finalfive.friendsup.viewmodels.GameViewModel

// There is a bug that I found in which the
// game option will change the ui text
// but inside the onclick It has the
// Initial value of the game

/**
 * The buttons options for Trivia and Would you Rather
 * @param option represents the name of the option
 * @param onClick handler for the button click
 */
@Composable
fun QuestionOption(
    option: GameQuestionOption,
    gameViewModel: GameViewModel,
) {
    var containerHeight by remember {
        mutableStateOf(70.dp)
    }

    var isClicked by remember {
        mutableStateOf(false)
    }

    // Get the game
    val game = gameViewModel.game!!

    // Get the current option being displayed
    var currentOption by remember {
        mutableStateOf(option)
    }

    // Every time the game progress changes (goes to the next question)
    // Set the is Clicked to false
    // Set the value of currentOption to option
    LaunchedEffect(key1 = game.gameProgress) {
        gameViewModel.questionAnswered = false
        isClicked = false
        currentOption = option
        // reset the current size when the game ends
        // We don't want to run this when the game first starts
        if(game.gameProgress > 0) {
            containerHeight = 70.dp
        }
    }

    // Every time the current option is changed
    // This will be affected by both users change
    // set is clicked to false if the button is not pressed
    // set is clicked to true if the button is pressed
    // Set the value of currentOption to option
    LaunchedEffect(key1 = option) {
        isClicked = option.selectedBy.isNotEmpty()
        currentOption = option
    }

    // The entire button size (with the darker color as the background)
    Box(
        // Darker part of the button
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(containerHeight)
            .clip(RoundedCornerShape(25.dp))
            .clickable { }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        // Handle the option being selected
                        gameViewModel.handleAnswerGameOption(currentOption)
                    }
                )
            }
            .background(colorResource(R.color.dark_purple))
    ) {
        // Lighter color on the left of the button
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(colorResource(R.color.light_purple))
        )

        if (isClicked) {
            ButtonAnimation()
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            // Text inside the button
            Text(
                text = currentOption.optionText,
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(start = 65.dp)
                    .padding(bottom = 10.dp),
                onTextLayout = {
                    if(it.didOverflowHeight) {
                        containerHeight = it.size.height.dp - containerHeight
                    }
                }
            )

            // Display image of user who selected this option
            Row {
                currentOption.selectedBy.forEach { selectedBy ->
                    game.members.forEach {
                        if(it.username == selectedBy) {
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

@Preview
@Composable
fun Preview(onClick: () -> Unit = {}) {
    var isClicked by remember {
        mutableStateOf(false)
    }

    // The entire button size (with the darker color as the background)
    Box(
        // Darker part of the button
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(25.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onClick()
                        isClicked = true
                    }
                )
            }
            .background(colorResource(R.color.dark_purple))
    ) {
        // Lighter color on the left of the button
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(colorResource(R.color.light_purple))
        )

        // Text inside the button
        Text(
            text = "Option",
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