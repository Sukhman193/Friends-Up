package ca.finalfive.friendsup.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ca.finalfive.friendsup.R

@Composable
fun GameQueueScreen(
    navController: NavController,
) {
    Text(
        text = stringResource(id = R.string.trivia_queue_search),
        style = MaterialTheme.typography.h1)
}