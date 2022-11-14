package ca.finalfive.friendsup.composables.queuescreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R

@Composable
fun SearchCardContent() {
    // Container of the text
    Row (
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        // Icon
        Icon(
            painter = painterResource(id = R.drawable.reload_icon),
            contentDescription = null,
            modifier = Modifier.size(32.dp).offset(y = 5.dp),
            tint = Color.White)
        // Text of the card
        Text(
            text = stringResource(id = R.string.trivia_queue_search),
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.h3,
        )
    }
}