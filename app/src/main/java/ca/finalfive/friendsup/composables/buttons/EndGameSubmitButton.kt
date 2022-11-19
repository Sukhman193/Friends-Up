package ca.finalfive.friendsup.composables.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R

// Reference for tenary condition
// https://stackoverflow.com/questions/16336500/how-to-write-ternary-conditional-operator

@Composable
        /**
         * End game screen button component
         */
fun EndGameSubmitButton(
    value: Int,
    icon: Int,
    modifier: Modifier = Modifier,
    buttonEnabled: Boolean = true,
    action: ()->Unit
) {
    Card(
        elevation = if(buttonEnabled) 9.dp else 0.dp,
        shape = RoundedCornerShape(20.dp),
        // Change card color in case the button is disabled
        modifier = Modifier.alpha(if(buttonEnabled) 1f else 0.65f)
    ) {
        // Display button
        Button(
            onClick = action,
            modifier = modifier.size(width = 230.dp, height = 90.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.light_purple),
                disabledBackgroundColor = colorResource(id = R.color.light_purple)
            ),
            enabled = buttonEnabled
        ) {
            // Put the button in a row
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Text of the button
                Text(text = stringResource(id = value))
                // icon of the button
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp), 
                    tint = colorResource(id = R.color.white)
                )

            }
        }
    }
}