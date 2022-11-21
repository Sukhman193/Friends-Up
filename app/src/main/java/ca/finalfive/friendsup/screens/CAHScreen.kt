package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.gamescreen.GameScreen
import ca.finalfive.friendsup.viewmodels.GameViewModel

@Composable
fun CAHScreen(gameViewModel: GameViewModel) {
    // Game being played
    val game = gameViewModel.game!!
    // Index of the game being played
    val currentGameIndex = game.gameProgress
    // Question of the current trivia being played
    val blackCard = game.gameContent[currentGameIndex].mainQuestion
    // Answers of the current trivia being played
    val whiteCards = game.gameContent[currentGameIndex].questionOptions

    GameScreen(
        gameTitle = R.string.game_cards_against_humanity_title_short,
        gameType = R.string.game_cards_against_humanity_type,
        titleFontSize = 60.sp,
        gameViewModel = gameViewModel,
        gameTimer = 10f
    )
    {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(400.dp)
        ) {

            //answer card #1 -> top left of screen
            Card(
                modifier = Modifier
                    .height(175.dp)
                    .width(135.dp)
                    .align(Alignment.TopStart)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp
            ) {
                Text(
                    text = whiteCards[0].optionText,
                    modifier = Modifier.padding(20.dp),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }

            //answer card #2 -> top right of screen
            Card(
                modifier = Modifier
                    .height(175.dp)
                    .width(135.dp)
                    .align(Alignment.TopEnd)
                    .verticalScroll(rememberScrollState()),

                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp
            ) {
                Text(
                    text = whiteCards[1].optionText,
                    modifier = Modifier.padding(20.dp),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }

            //answer card #3 -> bottom left of screen
            Card(
                modifier = Modifier
                    .height(175.dp)
                    .width(135.dp)
                    .align(Alignment.BottomStart)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp
            ) {
                Text(
                    text = whiteCards[2].optionText,
                    modifier = Modifier.padding(20.dp),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }

            //answer card #4 -> bottom right of screen
            Card(
                modifier = Modifier
                    .height(175.dp)
                    .width(135.dp)
                    .align(Alignment.BottomEnd)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp,
            ) {
                Text(
                    text = whiteCards[3].optionText,
                    modifier = Modifier.padding(20.dp),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }

            //the black question card that will display in the middle
            Card(
                modifier = Modifier
                    .zIndex(100F)
                    .align(Alignment.Center)
                    .height(175.dp)
                    .width(120.dp)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.black),
                elevation = 10.dp
            ) {
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