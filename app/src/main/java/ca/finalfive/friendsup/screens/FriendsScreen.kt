package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.FriendButton
import ca.finalfive.friendsup.composables.ScreenTitle
import ca.finalfive.friendsup.navigation.Route

/**
 * This screen displays a list of friends for the user
 * @param navController is the navigation controller of the application
 * @param friends is a list of friends that the user has
 */
@Composable
fun FriendsScreen(navController: NavController, friends: List<String>) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
    ) {
        // display the title of the screen
       ScreenTitle(title = R.string.friends_title)

        // Add description to the screen
        Text(
            text = stringResource(id = R.string.friends_description),
            fontSize = 19.sp,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 40.dp)
        )

        // Display a message if there are no friends
        if(friends.isEmpty()) {
            Text(
                text = stringResource(id = R.string.friend_list_no_friends),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3
            )
        }
        // Calls the FriendButton composable for each friend the user has
        friends.forEach { friend ->
            FriendButton(friendName = friend) {
                navController.navigate(Route.FriendsScreen.route + "/$friend")
            }
        }
    }
}