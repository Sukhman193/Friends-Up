package ca.finalfive.friendsup.composables.gamescreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * The MessageBox composable is the TextField for people to message in
 * on the bottom of the games
 * @param gameViewModel viewModel for the game
 * @param modifier additional modifiers for the text field
 */
@Composable
fun MessageBox(
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    // Text string for the text field
    var text by remember {
        mutableStateOf("")
    }
    // Local context of the application
    val context = LocalContext.current

    // Text field for the user to type in
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
        // Set the colors of the message box
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.white),
            backgroundColor = colorResource(R.color.light_grey).copy(alpha = 0.2F),
            focusedIndicatorColor = Color.Transparent
        ),
        // button of the message component
        trailingIcon = {
            SendButton {
                // Send the message
                gameViewModel.sendMessage(text, context)
                // reset the value of text
                text = ""
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 20.dp)
    )
}