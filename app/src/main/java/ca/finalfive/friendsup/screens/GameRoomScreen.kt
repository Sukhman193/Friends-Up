package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.GameSelectionCard
import ca.finalfive.friendsup.composables.ScreenTitle
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.viewmodels.GameViewModel

@Composable
fun GameRoomScreen(navController: NavController, gameViewModel: GameViewModel) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ScreenTitle(title = R.string.game_title)
        Spacer(modifier = Modifier.height(50.dp))

        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            GameSelectionCard(
                gameName = stringResource(id = R.string.game_trivia_title),
                gameDesc = stringResource(id = R.string.game_trivia_description),
                gameViewModel = gameViewModel,
                navController = navController,
                gameMode = GameMode.TRIVIA
            )
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_prompt_title),
                gameDesc = stringResource(id = R.string.game_prompt_description),
                gameViewModel = gameViewModel,
                navController = navController,
                gameMode = GameMode.PROMPT
            )
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_would_you_rather_title),
                gameDesc = stringResource(id = R.string.game_would_you_rather_description),
                gameViewModel = gameViewModel,
                navController = navController,
                gameMode = GameMode.WOULD_YOU_RATHER
            )
            GameSelectionCard(
                gameName = stringResource(id = R.string.game_cards_against_humanity_title),
                gameDesc = stringResource(id = R.string.game_cards_against_humanity_description),
                gameViewModel = gameViewModel,
                navController = navController,
                gameMode = GameMode.CARDS_AGAINST_HUMANITY
            )
        }
    }
}