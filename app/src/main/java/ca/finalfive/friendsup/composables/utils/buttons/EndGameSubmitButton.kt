package ca.finalfive.friendsup.composables.utils.buttons

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

// Reference for ternary condition
// https://stackoverflow.com/questions/16336500/how-to-write-ternary-conditional-operator

/**
 * End game screen button component
 * @param value Value of the button
 * @param icon Icon of the button
 * @param modifier Modifier for the button
 * @param isButtonEnabled States whether the button is enabled or not
 * @param action onClick event for the button
 */
@Composable
fun EndGameSubmitButton(
    value: Int,
    icon: Int,
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    action: () -> Unit
) {
    // Container of the button
    // Used to add elevation to the button
    Card(
        elevation = if (isButtonEnabled) 9.dp else 0.dp,
        shape = RoundedCornerShape(20.dp),
        // Change card color in case the button is disabled
        modifier = Modifier.alpha(if (isButtonEnabled) 1f else 0.65f)
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
            enabled = isButtonEnabled
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