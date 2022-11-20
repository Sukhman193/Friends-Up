package ca.finalfive.friendsup.composables.queuescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.composables.ScreenTitle
import ca.finalfive.friendsup.ui.theme.redSubmitButton

/**
 * Queue Screen Template
 * @param timer current timer in seconds
 * @param screenTitleText title of the screen
 * @param submitButtonContent content of the submit button,
 * integer which refers to the value in the `string.xml`
 * @param submitAction action taken by clicking the submit button
 * @param cardContent content of the card
 */
@Composable
fun QueueScreenTemplate(
    timer: Int,
    screenTitleText: Int,
    submitButtonContent: Int,
    submitAction: () -> Unit,
    cardContent: @Composable () -> Unit
) {
    /**
     * Get the remaining time in a user friendly format
     * @param seconds Number of seconds for the timer
     */
    fun getTimerFormatted(seconds: Int): String {
        // Get how many minutes are in the time
        val min = seconds / 60
        // Get the remaining seconds
        val remainingSeconds = seconds % 60
        // if the seconds are single digits add a 0 value
        val addZero = if (remainingSeconds.toString().length == 1) {
            "0"
        } else {
            ""
        }
        // Return formatted timer
        return "${min}:${addZero}${remainingSeconds}"
    }

    Box {
        // Add background image
        BackgroundImage(showMoon = true)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add title
            ScreenTitle(title = screenTitleText)
            // Search card and timer container
            Column(
                horizontalAlignment = Alignment.End
            ) {
                // Queue search for players card
                OffsetCard(
                    modifier = Modifier
                        .size(width = 340.dp, height = 75.dp),
                    containerModifier = Modifier.padding(top = 40.dp)
                ) {
                    // Content of the card
                    cardContent()
                }

                // Queue timer display
                OffsetCard(
                    modifier = Modifier
                        .size(width = 75.dp, height = 40.dp),
                    containerModifier = Modifier.padding(top = 10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Timer
                        Text(
                            text = getTimerFormatted(timer),
                            style = MaterialTheme.typography.caption,
                            fontSize = 20.sp
                        )
                    }
                }
            }
            // Add some space
            Spacer(modifier = Modifier.height(50.dp))
            // Timer image for decoration
            Image(
                painter = painterResource(id = R.drawable.timer),
                contentDescription = null,
                modifier = Modifier.size(210.dp)
            )

            // Add space
            Spacer(modifier = Modifier.height(50.dp))
            // Submit button
            Button(
                onClick = submitAction,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = redSubmitButton
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                // Text of the button
                Text(
                    text = stringResource(id = submitButtonContent),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 5.dp),
                )
            }
        }
    }
}