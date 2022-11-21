package ca.finalfive.friendsup.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.composables.FriendButton

/**
 * This screen displays a list of friends for the user
 * @param navController is the navigation controller of the application
 * @param friends is a list of friends that the user has
 */
@Composable
fun FriendsScreen(navController: NavController, friends: List<String>) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        Row {
            Column(modifier = Modifier) {
                Text(
                    text = "Friend List",
                    color = Color.White,
                    style = MaterialTheme.typography.h1,
                    fontSize = 55.sp,
                    modifier = Modifier
                        .padding(start = 140.dp, top = 20.dp)
                )

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .padding(vertical = 3.dp, horizontal = 32.dp)
                ) {
                    // 20f + 240f + 560f = 820f
                    // The first line which starts at 0f and ends at 20f (White)
                    drawLine(
                        color = Color.White,
                        start = Offset(320f, 0f),
                        end = Offset(340f, 0f),
                        strokeWidth = 10f,
                        cap = StrokeCap.Round
                    )
                    // The second line which starts at 60f and ends at 300f (White)
                    drawLine(
                        color = Color.White,
                        start = Offset(370f, 0f),
                        end = Offset(600f, 0f),
                        strokeWidth = 10f,
                        cap = StrokeCap.Round
                    )
                    // The third line which starts at 340f and ends at 900f (White)
                    drawLine(
                        color = Color.White,
                        start = Offset(630f, 0f),
                        end = Offset(900f, 0f),
                        strokeWidth = 10f,
                        cap = StrokeCap.Round
                    )
                }

                Text(text = "Get in contact with your friends",
                    fontSize = 19.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 40.dp))

                // Calls the FriendButton composable for each friend the user has
                for (friend in friends) {
                    FriendButton(friendName = friend)
                }
            }
        }
    }
}