package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.composables.ScreenTitle
import ca.finalfive.friendsup.composables.buttons.CustomRadioButton
import ca.finalfive.friendsup.composables.buttons.ReportSubmitButton
import ca.finalfive.friendsup.composables.drawings.LineSeparator
import ca.finalfive.friendsup.navigation.Route

/**
 * Report screen for reporting the users
 * @param navController Navigation controller for the application
 */
@Composable
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
            // Add description of the screen
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp, vertical = 50.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Display Report page subtitle
                    Text(
                        text = stringResource(id = R.string.report_user_reason_title),
                        style = MaterialTheme.typography.h3,
                    )
                    // Display Report page description
                    Text(
                        text = stringResource(id = R.string.report_user_description),
                        style = MaterialTheme.typography.caption,
                        fontSize = 12.sp,
                        lineHeight = 20.sp
                    )
                    // Add a line separator
                    LineSeparator()
                    Spacer(modifier = Modifier.height(30.dp))
                    // Show all the radio button options
                    radioOptions.forEach { text ->
                        // Use the Custom Radio Button to display the option
                        CustomRadioButton(
                            text = text,
                            selectedOption = selectedOption,
                            setOptionSelected = setOptionSelected
                        )
                    }
                }

                // Report button
                ReportSubmitButton(text = R.string.button_report) {
                    // TODO: Action for reporting the user and navigate to the game page
                    navController.navigate(Route.GameRoomScreen.route)
                }
            }
        }
    }
}

