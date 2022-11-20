package ca.finalfive.friendsup.composables

import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.animations.ButtonAnimation
import ca.finalfive.friendsup.models.GameQuestionOption
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

/**
 * The buttons options for Trivia and Would you Rather
 * @param option represents the name of the option
 * @param onClick handler for the button click
 */
@Composable
fun QuestionOption(
    option: GameQuestionOption,
    gameViewModel: GameViewModel
) {
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
            .clickable { }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        if (!gameViewModel.questionAnswered) {
                            Log.d("TEST.... PLEASEEEEE", "RUNNING MANNNN")
                            // Handle the option being selected
                            gameViewModel.handleAnswerGameOption(option)
                            //
                            isClicked = true
                        }
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
            ButtonAnimation(gameViewModel = gameViewModel, isQuestionAnswered = gameViewModel.questionAnswered)
        }

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