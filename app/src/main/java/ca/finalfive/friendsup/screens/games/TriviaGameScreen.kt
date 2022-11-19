package ca.finalfive.friendsup.screens.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ca.finalfive.friendsup.composables.ReportPopup
import ca.finalfive.friendsup.viewmodels.GameViewModel

@Composable
fun TriviaGameScreen(gameViewModel: GameViewModel) {
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
        Button(onClick = { gameViewModel.sendMessage("EHLLLOOOOO") }) {
            Text(text = "SEND MESSAGE")
        }
        Button(onClick = {
            setOpenReportPopup(true)
        }) {
            Text(text = "Report User")
        }
    }
}