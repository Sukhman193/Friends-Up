package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.models.Chat
import coil.compose.rememberAsyncImagePainter

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
            modifier = Modifier.width(150.dp)
        ) {
            // name of the user
            Text(
                text = chat.sentBy,
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 14.sp,
            )
            // chat of the user
            Text(
                text = chat.content,
                color = Color.White,
                style = MaterialTheme.typography.caption,
                fontSize = 11.sp,
            )
        }
    }
}

// https://stackoverflow.com/questions/66835210/change-layout-direction-of-a-composable
@Composable
        /**
         * Row layout component with a reverse functionality
         * @param modifier Modifier for the row
         * @param horizontalArrangement Arrangement for the row
         * @param verticalAlignment Alignment for the row
         * @param reversed States whether the row should be reversed or not
         * @param content Content of the row
         */
fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    reversed: Boolean,
    content: @Composable RowScope.() -> Unit,
) {
    // Get the layout direction for the row
    val direction = if(reversed) LayoutDirection.Rtl else LayoutDirection.Ltr
    // Change the composition's layout direction
    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            // Display the content of the row
            content()
        }
    }
}

@Preview
@Composable
fun testing() {
    MessageCard(chat = Chat("","Sahand", icon = "https://treenewal.com/wp-content/uploads/2020/11/oak-tree-care.png", content = "hello my nam eis sahasnd"), isSender = false )
}