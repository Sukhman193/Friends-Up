package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.GameSelectionCard
import ca.finalfive.friendsup.navigation.Route
import ca.finalfive.friendsup.viewmodels.GameViewModel

// TODO: Remove this screen and put the actual screen
@Composable
fun GameRoomScreen(navController: NavController, gameViewModel: GameViewModel) {
    Column {
        Text(
            text = "Game Room",
            style = MaterialTheme.typography.h1,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 150.dp),
            fontSize = 60.sp
        )
        Spacer(modifier = Modifier.height(50.dp))

        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_trivia_title),
                gameDesc = stringResource(id = R.string.game_trivia_description),
                gameViewModel = gameViewModel,
                navController = navController
            )
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_prompt_title),
                gameDesc = stringResource(id = R.string.game_prompt_description),
                gameViewModel = gameViewModel,
                navController = navController
            )
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_would_you_rather_title),
                gameDesc = stringResource(id = R.string.game_would_you_rather_description),
                gameViewModel = gameViewModel,
                navController = navController
            )
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_cards_against_humanity_title),
                gameDesc = stringResource(id = R.string.game_cards_against_humanity_description),
                gameViewModel = gameViewModel,
                navController = navController
            )
        }
    }
}