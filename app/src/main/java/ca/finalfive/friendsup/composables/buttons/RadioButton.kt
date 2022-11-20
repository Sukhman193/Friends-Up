package ca.finalfive.friendsup.composables.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Custom Radio Button
 * @param text Text of the radio button
 * @param selectedOption currently selected option of the radio buttons
 * @param setOptionSelected change the currently selected option to the newly selected option
 */
@Composable
fun CustomRadioButton(
    text: Int,
    selectedOption: Int,
    setOptionSelected: (Int) -> Unit
) {
    // Container for the radio buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = (text == selectedOption),
                onClick = {
                    setOptionSelected(text)
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Radio button option
        RadioButton(
            selected = (text == selectedOption),
            onClick = { setOptionSelected(text) },
            colors = RadioButtonDefaults.colors(
                unselectedColor = Color.White,
            )
        )
        // Text of the radio button
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}