package ca.finalfive.friendsup.composables.gamescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * The MessageBox composable is the TextField for people to message in
 * on the bottom of the games
 * @param gameViewModel viewModel for the game
 * @param modifier additional modifiers for the text field
 * @param requester Focus requester for the keyboard
 * @param setKeyboardShown Set whether the keyboard is shown or not
 * @param localFocusManager Local focus manager for clearing the keyboard
 */
@Composable
fun MessageBox(
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier,
    requester: FocusRequester,
    setKeyboardShown: (Boolean) -> Unit,
    localFocusManager: FocusManager
) {
    // Text string for the text field
    var text by remember {
        mutableStateOf("")
    }
    // Local context of the application
    val context = LocalContext.current
    // Text field for the user to type in
    Box(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .imePadding()
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Type message here",
                    color = Color.White
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(R.color.light_grey).copy(alpha = 0.2F),
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(Color.White),
            trailingIcon = {
                SendButton {
                    gameViewModel.sendMessage(
                        context = context,
                        content = text
                    )
                    text = ""
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    localFocusManager.clearFocus()
                }
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 20.dp)
                .focusRequester(requester)
                .onFocusChanged { setKeyboardShown(it.isFocused) })
    }
}