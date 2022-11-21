package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.composables.DropShadowText
import ca.finalfive.friendsup.composables.ScreenTitle
import ca.finalfive.friendsup.composables.utils.FriendDetailCard
import ca.finalfive.friendsup.models.FriendCardDetail
import ca.finalfive.friendsup.models.User
import ca.finalfive.friendsup.viewmodels.UserViewModel

/**
 * Friend detail screen which displays all the details of a friend
 * @param userViewModel view model of the user
 * @param friend friend to display the details of
 */
@Composable
fun FriendDetailScreen(userViewModel: UserViewModel, friend: User) {
    // Make a list for the friends details
    var friendDetails = listOf(
        FriendCardDetail("SnapChat", friend.snapchat, R.drawable.snapchat_icon),
        FriendCardDetail("Instagram", friend.instagram, R.drawable.instagram_icon),
        FriendCardDetail("Discord", friend.discord, R.drawable.discord_icon),
        FriendCardDetail("Phone Number", friend.phone, R.drawable.phone_icon),
    )

    // filter out all the friends details that are not filled out
    friendDetails = friendDetails.filter { it.value != "" }

    // Container of the application
    Box {
        // Set the background of the screen
        BackgroundImage(showMoon = true)
        // container used to put the stuff in a column
        Column(
            modifier = Modifier.padding(bottom = 120.dp),
        ) {
            // Title of the screen
            ScreenTitle(title = R.string.friend_detail_title)
            // user's username
            DropShadowText(
                text = friend.username,
                style = MaterialTheme.typography.h3,
                fontSize = 45.sp,
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            // Container for the card
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Display all the components
                friendDetails.forEach {
                    FriendDetailCard(friendCardDetail = it)
                }
            }
        }

        // Remove friend button
        Button(
            onClick = {
                      // TODO: MAKE THE BELOW FUNCTION
//                userViewModel.removeFriend(friend.username)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.cancelRed)
            ),
            modifier = Modifier
                .padding(vertical = 40.dp)
                .clip(RoundedCornerShape(30.dp))
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.button_remove_friend),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

