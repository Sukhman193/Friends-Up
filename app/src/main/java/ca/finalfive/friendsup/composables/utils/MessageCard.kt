package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.helpers.Row
import ca.finalfive.friendsup.models.Chat
import coil.compose.rememberAsyncImagePainter


// https://medium.com/geekculture/jetpack-compose-image-loading-using-coil-647a8098c217
// https://coil-kt.github.io/coil/
/**
 * MessageCard - A component to display the messages between two users
 * @param index - it calculates the opacity of the messages from new to old up to 5 messages
 * @param chat - User's chat content + profile picture + name
 * @param isSender - A boolean state to specify if the user is the sender or receiver
 * @param listCount - Total number of chats inside the list
 * @param isOpacityEnabled -
 */
@Composable
fun MessageCard(
    index: Int,
    chat: Chat,
    isSender: Boolean,
    listCount: Int,
    isOpacityEnabled: Boolean){

    // calculates the opacity of the message card
    val opacity = if(isOpacityEnabled) {
        (index + 1) / listCount.toFloat()
    } else {
        1f
    }

    // Row to show the message contents
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 20.dp, end = 140.dp)
            // the opacity of the message component
            .alpha(opacity)
        ,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        // based on the sender it reverses the chat
        reversed = isSender
    ) {
        // image of the user
        Card(
            shape = CircleShape,
            modifier = Modifier.size(45.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = chat.icon,
                    contentScale = ContentScale.Crop,
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,

            )
        }
        Column {
            // name of the user
            Text(
                text = chat.sentBy,
                color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
            // chat of the user
            Text(
                text = chat.content,
                color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.caption,
                fontSize = 12.sp,
            )
        }
    }
}