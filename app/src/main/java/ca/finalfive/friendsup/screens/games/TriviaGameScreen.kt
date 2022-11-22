package ca.finalfive.friendsup.screens.games

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.QuestionOption
import ca.finalfive.friendsup.composables.gamescreen.GameScreen
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * Screen for the trivia game
 * @param gameViewModel view model for the game controls
 */
@Composable
fun TriviaGameScreen(gameViewModel: GameViewModel) {
    // Game being played
    var game = gameViewModel.game!!
    // Index of the game being played
    var currentGameIndex = game.gameProgress
    // Question of the current trivia being played
    var question by remember {
        mutableStateOf(game.gameContent[currentGameIndex].mainQuestion)
    }
    // Answers of the current trivia being played
    var answers by remember {
        mutableStateOf(game.gameContent[currentGameIndex].questionOptions)
    }
    // Get the correct answer of the game
    var correctAnswer by remember {
        mutableStateOf(game.gameContent[currentGameIndex].questionOptions.last())
    }
    // States whether to display the correct answer or not
    // this is going to become true once both users
    // select a game
    var displayCorrectAnswer by remember {
        mutableStateOf(false)
    }

    // every time the game progresses update the data about games
    LaunchedEffect(key1 = gameViewModel.game?.gameProgress) {
        game = gameViewModel.game!!
        currentGameIndex = game.gameProgress
        question = game.gameContent[currentGameIndex].mainQuestion
        answers = game.gameContent[currentGameIndex].questionOptions.shuffled()
        correctAnswer = game.gameContent[currentGameIndex].questionOptions.last()
    }

    // Every time the game content updates, like the user who selected
    // the fields update the answers
    LaunchedEffect(key1 = gameViewModel.game?.gameContent?.get(currentGameIndex)?.questionOptions) {
        // Get the updated game content
        val gameQuestionContent = gameViewModel.game!!.gameContent[currentGameIndex].questionOptions
        // update shuffled answer's selected by for each question
        answers = answers.map { a ->
            gameQuestionContent.first { it.optionText == a.optionText }
        }
        // check whether to display the answer or not
        // First we get all the questions answered
        // if the total number of questions answered
        // equals to the number of total members than it's true
        displayCorrectAnswer = gameQuestionContent.map {
            it.selectedBy.isNotEmpty()
        }.filter { it }.size == game.members.size
    }

    GameScreen(
        gameTitle = R.string.game_trivia_title,
        gameType = R.string.game_trivia_type,
        titleFontSize = 60.sp,
        gameViewModel = gameViewModel,
        gameTimer = 10f
    ) {
        // Question for the user
        Text(
            text = question,
            color = Color.White,
            style = MaterialTheme.typography.h3,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(horizontal = 30.dp)
        )

        // Space between the question and the question options
        Spacer(
            modifier = Modifier
                .padding(bottom = 30.dp)
        )

        // A for loop containing 4 button options
        answers.forEach { answer ->
            QuestionOption(
                option = answer,
                gameViewModel = gameViewModel,
                correctAnswer = correctAnswer,
                displayCorrectAnswer = displayCorrectAnswer
            )
        }
    }
}


