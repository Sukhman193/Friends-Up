package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.helpers.Row
import ca.finalfive.friendsup.models.Chat
import coil.compose.rememberAsyncImagePainter
import ca.finalfive.friendsup.R


// https://medium.com/geekculture/jetpack-compose-image-loading-using-coil-647a8098c217
// https://coil-kt.github.io/coil/
/**
 * MessageCard - A component to display the messages between two users
 * @param index - it calculates the opacity of the messages from new to old up to 5 messages
 * @param chat - User's chat content + profile picture + name
 * @param isSender - a boolean state to specify if the user is the sender or receiver
 */
@Composable
fun MessageCard(index: Int = 4, chat: Chat, isSender: Boolean){
    // calculates the opacity
    val opacity =  (index + 1) / 5.0f
    // Row to show the message contents
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 20.dp, end = 140.dp)
            .background(Color.Blue)
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
                painter = rememberAsyncImagePainter(chat.icon),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,

            )
        }
        Column(
//            modifier = Modifier.
        ) {
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