package ca.finalfive.friendsup.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * GameScreen is the default game screen for all the different kinds of
 * games, which contains the Top bar, Game Timer animation and the Message Box
 * @param gameTitle is the title at the Top bar
 * @param gameType is the variable beside the numbers in the timer animation
 *      for example "<Question> 1 of 5". gameType being the word Question
 * @param titleFontSize font size for the title
 * @param gameTimer timer of the game
 */
@Composable
fun GameScreen(
    gameTitle: Int,
    gameType: Int,
    titleFontSize: TextUnit,
    gameTimer: Float = 90f,
    gameViewModel: GameViewModel,
    gameContent: @Composable () -> Unit
) {

    // saves the state of Focus Request for the keyboard
    val requester = remember {
        FocusRequester()
    }

    // States of keyboard
    val (isKeyboardShown, setKeyboardShown) = remember { mutableStateOf(false) }

    // saves the state of the local focus
    val localFocusManager = LocalFocusManager.current

    // Black background over the background to make the background dimmer
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5F))
    )

    // Scaffold used to put the game inside the game screen
    Scaffold(
        // The bottom bar will be the bottom text box for sending messages
        bottomBar = {
            BottomMessage(
                requester = requester,
                setKeyboardShown = setKeyboardShown,
                gameViewModel = gameViewModel
            )
        },
        // The top bar will include the title of the game as well
        // as the timer
        topBar = {
            Counter(
                gameTitle = gameTitle,
                titleFontSize = titleFontSize,
                gameTimer = gameTimer,
                gameType = gameType,
                gameViewModel = gameViewModel
            )
        },
        // Set the background to be transparent
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // checks if the user taps outside the textField
                detectTapGestures {
                    localFocusManager.clearFocus()
                }
            },
    ) { padding ->
        // Set the padding to the content of the game
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                // add extra padding at the bottom
                .padding(bottom = 20.dp)
        ) {
            if (!isKeyboardShown) {
                // The content for which ever game is used, such as cards for
                // Cards Against Humanities, or Question Options for Trivia
                // and Would you Rather
                gameContent()
            }
        }
    }
}

/**
 * The top of the game screen, it will keep track of the time and everything
 * @param gameTitle is the title at the Top bar
 * @param gameType is the variable beside the numbers in the timer animation
 *      for example "<Question> 1 of 5". gameType being the word Question
 * @param titleFontSize font size for the title
 * @param gameTimer timer of the game
 */
@Composable
fun Counter(
    gameTitle: Int,
    titleFontSize: TextUnit,
    gameTimer: Float,
    gameType: Int,
    gameViewModel: GameViewModel
) {
    Column {
        // Container at the top which includes the title of the mini-game
        TopGameBar(
            gameTitle = gameTitle,
            fontSize = titleFontSize
        )

        // Space between the 3 lines and the top bar
        Spacer(
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        // Component containing the timer animation along with the lines above it,
        // and the words to the left of the timer
        GameTimer(
            totalTime = gameTimer,
            prompt = gameType,
            gameViewModel = gameViewModel
        )
    }
}

/**
 * Message text box for the content
 * @param requester Focus requester for the keyboard
 * @param setKeyboardShown Set whether the key is shown or not
 */
@Composable
fun BottomMessage(
    gameViewModel: GameViewModel,
    requester: FocusRequester,
    setKeyboardShown: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .imePadding()
    ) {
        MessageBox(
            gameViewModel = gameViewModel,
            modifier = Modifier
            .focusRequester(requester)
            .onFocusChanged { setKeyboardShown(it.hasFocus) })
    }
}