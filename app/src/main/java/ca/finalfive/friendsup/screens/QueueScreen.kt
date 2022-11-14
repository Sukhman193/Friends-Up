package ca.finalfive.friendsup.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.queuescreen.QueueScreenTemplate
import ca.finalfive.friendsup.composables.queuescreen.SearchCardContent
import kotlinx.coroutines.delay

// https://www.geeksforgeeks.org/how-to-create-a-timer-using-jetpack-compose-in-android/

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

    QueueScreenTemplate(
        timer = timer,
        screenTitleText = R.string.trivia_queue_title,
        submitButtonContent = R.string.button_leave,
        submitAction = {
            navController.popBackStack()
        }) {
        SearchCardContent()
    }
}