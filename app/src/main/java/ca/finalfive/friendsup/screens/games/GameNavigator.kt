package ca.finalfive.friendsup.screens.games

import androidx.compose.runtime.Composable
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * Handles the navigation to the correct game
 * @param gameViewModel view model for the game
 */
@Composable
fun GameNavigator(gameViewModel: GameViewModel) {
    // Check which game they are playing
    when (gameViewModel.game?.gameMode) {
        // Playing trivia game
        GameMode.TRIVIA -> {
            TriviaGameScreen(gameViewModel)
        }
        // Playing Prompt game
        GameMode.PROMPT -> {
            PromptGameScreen(gameViewModel)
        }
        // Playing Would you rather game
        GameMode.WOULD_YOU_RATHER -> {
            WYRGameScreen(gameViewModel)
        }
        // Playing Cards against humanity game
        GameMode.CARDS_AGAINST_HUMANITY -> {
            CAHScreen(gameViewModel)
        }
    }
}