package ca.finalfive.friendsup.screens.games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.gamescreen.GameScreen
import ca.finalfive.friendsup.composables.cah.WhiteCard
import ca.finalfive.friendsup.viewmodels.GameViewModel


/**
 * Screen for the CAH game
 * @param gameViewModel game viewModel for the application
 */
@Composable
fun CAHScreen(gameViewModel: GameViewModel) {
    if (gameViewModel.game == null) {
        return
    }

    // Game being played
    var game by remember {
        mutableStateOf(gameViewModel.game!!)
    }
    // Index of the game being played
    var currentGameIndex by remember {
        mutableStateOf(game.gameProgress)
    }
    // Question of the current trivia being played
    var blackCard by remember {
        mutableStateOf(game.gameContent[currentGameIndex].mainQuestion)
    }

    var whiteCards by remember {
        mutableStateOf(game.gameContent[currentGameIndex].questionOptions)
    }


    // Every time the game progress changes update the data
    LaunchedEffect(key1 = gameViewModel.game?.gameProgress) {
        // update the game
        game = gameViewModel.game!!
        // update the current index
        currentGameIndex = game.gameProgress
        // Update the black card
        blackCard = game.gameContent[currentGameIndex].mainQuestion
        // Update the white cards
        whiteCards = game.gameContent[currentGameIndex].questionOptions
        // set the question answered to false
        gameViewModel.questionAnswered = false
    }

    // Every time a user selects a game the display
    LaunchedEffect(key1 = gameViewModel.game?.gameContent?.get(currentGameIndex)) {
        game = gameViewModel.game!!
        whiteCards = game.gameContent[currentGameIndex].questionOptions
    }

    //Everytime a card is tapped, we want to display a message to double tap

    // Container of the screen
    GameScreen(
        gameTitle = R.string.game_cards_against_humanity_title_short,
        gameType = R.string.game_cards_against_humanity_type,
        titleFontSize = 60.sp,
        gameViewModel = gameViewModel,
        gameTimer = 45f
    ) {
        // Container of the game playing area
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(400.dp)
        ) {
            // Add the first white card
            WhiteCard(
                gameViewModel = gameViewModel,
                align = Alignment.TopStart,
                whiteCard = whiteCards[0],
                game = game,
            )
            // Add the second white card
            WhiteCard(
                gameViewModel = gameViewModel,
                align = Alignment.TopEnd,
                whiteCard = whiteCards[1],
                game = game
            )
            // Add the third white card
            WhiteCard(
                gameViewModel = gameViewModel,
                whiteCard = whiteCards[2],
                game = game,
                align = Alignment.BottomStart,
            )
            // Add the fourth white card
            WhiteCard(
                gameViewModel = gameViewModel,
                whiteCard = whiteCards[3],
                game = game,
                align = Alignment.BottomEnd,
            )

            //the black question card that will display in the middle
            Card(
                modifier = Modifier
                    .zIndex(100f)
                    .align(Alignment.Center)
                    .height(175.dp)
                    .width(120.dp)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.black),
                elevation = 10.dp
            ) {
                // Content of the white card
                Text(
                    text = blackCard,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(10.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}