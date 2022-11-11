package ca.finalfive.strangercommons.composables.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.strangercommons.ui.theme.darkPurple
import ca.finalfive.strangercommons.ui.theme.lightPurple

@Composable
        /**
         * Submit button for the Report Screen
         * @param text ID of the string.xml corresponding to the string
         * @param onClick Handle the click event
         */
fun ReportSubmitButton(text: Int, onClick: () -> Unit) {
    // Container for the button, which helps aligning the button to the center
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Background for the offset of the button
        Card(
            modifier = Modifier
                .height(55.dp)
                .width(225.dp),
            backgroundColor = lightPurple,
            shape = RoundedCornerShape(8.dp)
        ) {
            // Report button
            Button(
                onClick = onClick,
                modifier = Modifier.padding(bottom = 4.dp, start = 2.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = darkPurple
                )
            ) {
                // Text on top of the button
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.caption,
                    fontSize = 20.sp
                )
            }
        }
    }
}