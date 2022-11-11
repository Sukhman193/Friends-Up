package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.composables.BackgroundImage
import ca.finalfive.strangercommons.composables.drawings.LineSeparator
import ca.finalfive.strangercommons.composables.ScreenTitle
import ca.finalfive.strangercommons.composables.buttons.ReportSubmitButton

@Composable
        /**
         * Report screen for reporting the users
         * @param navController Navigation controller for the application
         */
fun ReportScreen(
    navController: NavController
) {
    // https://foso.github.io/Jetpack -Compose-Playground/material/radiobutton/
    /**
     * List of all the radio buttons options
     */
    val radioOptions = listOf(
        R.string.report_user_option_bullying,
        R.string.report_user_option_uncomfortable,
        R.string.report_user_option_suicide_self_harm,
        R.string.report_user_option_span
    )

    /**
     * Selection option of the current radio buttons
     */
    val (selectedOption, setOptionSelected) = remember { mutableStateOf(radioOptions[3] ) }

    // Container of the page
    Box {
        // Add background image
        BackgroundImage(showMoon = true)
        // Container of the screen
        Column {
            // Add title to the screen
            ScreenTitle(title = stringResource(id = R.string.report_user_title))
            Spacer(modifier = Modifier.height(50.dp))
            // Add description of the screen
            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                Text(
                    text = stringResource(id = R.string.report_user_reason_title),
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = stringResource(id = R.string.report_user_description),
                    style = MaterialTheme.typography.caption,
                    fontSize = 12.sp,
                    lineHeight = 20.sp
                )
                // Add a line separator
                LineSeparator()
                Spacer(modifier = Modifier.height(30.dp))
                // Iterate over the list of options
                radioOptions.forEach { text ->
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
                Spacer(modifier = Modifier.height(50.dp))
                // Report button
                ReportSubmitButton(text = R.string.button_report) {
                    // TODO: Action for reporting the user and navigate to the game page
                }
            }
        }
    }
}

