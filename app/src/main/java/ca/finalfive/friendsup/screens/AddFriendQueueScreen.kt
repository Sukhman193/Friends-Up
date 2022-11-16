package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.queuescreen.QueueScreenTemplate
import ca.finalfive.friendsup.composables.queuescreen.SearchCardContent
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.delay

@Composable
        /**
         * Queue Screen for adding a friend
         * The friend will automatically be added
         * when the both the users in the game add each other
         * @param gameViewModel view model for the game
         */
fun AddFriendQueueScreen(
    gameViewModel: GameViewModel
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
        screenTitleText = R.string.add_friend_title,
        submitButtonContent = R.string.button_cancel,
        submitAction = {
            gameViewModel.updateUserFriendQueue()
        }) {
        // Content of the search user
        SearchCardContent(){
            // content of the card
            Text(
                text = stringResource(id = R.string.add_friend_description),
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(200.dp),
                lineHeight = 19.sp,
            )
        }
    }
}