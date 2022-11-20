package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.queuescreen.QueueScreenTemplate
import ca.finalfive.friendsup.composables.queuescreen.SearchCardContent
import kotlinx.coroutines.delay

// https://www.geeksforgeeks.org/how-to-create-a-timer-using-jetpack-compose-in-android/

/**
 * Screen for queue for entering a game
 * @param navController Navigation controller for the application
 */
@Composable
fun GameQueueScreen(
    navController: NavController
) {
    /**
     * Timer for how long the user has been in the queue screen
     */
    var timer by remember {
        mutableStateOf(0)
    }

    // Increment the timer for the queue wait time
    LaunchedEffect(key1 = timer) {
        // Delay for one second
        delay(1000L)
        timer += 1
    }

    // Display the queue screen
    QueueScreenTemplate(
        timer = timer,
        screenTitleText = R.string.trivia_queue_title,
        submitButtonContent = R.string.button_leave,
        submitAction = {
            navController.popBackStack()
        }) {
        // Content of the search user
        SearchCardContent() {
            Text(
                text = stringResource(id = R.string.trivia_queue_search),
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.h3,
            )
        }
    }
}