package ca.finalfive.friendsup.screens.games

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import ca.finalfive.friendsup.composables.ReportPopup
import ca.finalfive.friendsup.viewmodels.GameViewModel

@Composable
fun TriviaGameScreen(gameViewModel: GameViewModel) {

    var gameContent by remember {
        mutableStateOf(gameViewModel.game!!.gameContent[0])
    }
    // Every time the content of the game progress changes update the
    // game content
    LaunchedEffect(
        key1 = gameViewModel.game!!.gameProgress,
        key2 = gameViewModel.game!!) {
        // update the game content
        gameContent = gameViewModel.game!!.gameContent[gameViewModel.game!!.gameProgress]
        Log.d("LLAMA", "INSIDE")
    }

    // Update the ability for the user to select an answer
    // if the question changes
    LaunchedEffect(key1 = gameViewModel.game!!.gameProgress) {

        gameViewModel.questionAnswered = false
    }

    // get updates regarding the game
    val (openReportPopup, setOpenReportPopup) = remember {
        mutableStateOf(false)
    }


    Box {
        if(openReportPopup) {
            ReportPopup(setReportPopup = setOpenReportPopup, gameViewModel)
        }
    }
    Column {
        Text(
            text = "TRIVIA Game Started",
            style = MaterialTheme.typography.h1)
        Button(onClick = { gameViewModel.sendMessage("Hello") }) {
            Text(text = "SEND MESSAGE")
        }
        Button(onClick = {
            setOpenReportPopup(true)
        }) {
            Text(text = "Report User")
        }
        Button(onClick = {
            gameViewModel.updateUserFriendQueue()
        }) {
            Text(text = "add to friend list")
        }
        Button(onClick = {
            gameViewModel.endGame()
        }) {
            Text(text = "EndGame")
        }
        Button(onClick = {
            gameViewModel.handleGameProgress()
        }) {
            Text(text = "NEXT GAME")
        }
        Text(text = gameContent.mainQuestion)
        gameContent.questionOptions.forEach {
            Button(
                onClick = {
                    gameViewModel.handleAnswerGameOption(it)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if(it.selectedBy.contains(gameViewModel.savedUsername)) {
                            Color.Blue
                        } else if(it.selectedBy.isNotEmpty()) {
                            Color.Red
                    } else {
                        Color.Black
                    }
                )
            ) {
                Text(text = it.optionText)
            }
        }
    }
}