package ca.finalfive.friendsup.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R

/**
 * The MessageBox composable is the TextField for people to message in
 * on the bottom of the games
 */
@Composable
fun MessageBox(modifier: Modifier = Modifier) {
    // Text string for the text field
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }

    // Text field for the user to type in
    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(
                text = "Type message here",
                color = Color.White
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(R.color.light_grey).copy(alpha = 0.2F),
            focusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(Color.White),
        trailingIcon = {
            SendButton {

            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 20.dp)
    )
}