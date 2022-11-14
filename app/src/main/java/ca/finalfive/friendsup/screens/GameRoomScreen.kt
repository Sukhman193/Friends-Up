package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.navigation.Route
import ca.finalfive.friendsup.viewmodels.GameViewModel

// TODO: Remove this screen and put the actual screen
@Composable
fun GameRoomScreen(navController: NavController, gameViewModel: GameViewModel) {
    Column {
        Text(
            text = "Chat Room",
            style = MaterialTheme.typography.h1,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 150.dp),
            fontSize = 60.sp
        )
        Button(onClick = {
            gameViewModel.joinGame()
            navController.navigate(Route.QueueScreen.route)
        }) {
            Text(text = "START TRIVIA GAME")
        }
    }
}