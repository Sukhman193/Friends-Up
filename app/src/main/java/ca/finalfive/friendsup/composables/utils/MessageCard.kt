package ca.finalfive.friendsup.composables.utils

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.models.Chat
import coil.compose.rememberAsyncImagePainter

@Composable
fun MessageCard(index: Int = 1, chat: Chat, isSender: Boolean){
    val messageAlignment = if (isSender) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier
            .width(250.dp)
            .alpha(index.toFloat())
        ,
        horizontalArrangement = messageAlignment.also { Arrangement.spacedBy(4.dp) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = rememberAsyncImagePainter(chat.icon),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
        Column() {
            Text(
                text = chat.sentBy,
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 14.sp
            )
            Text(
                text = chat.content,
                color = Color.White,
                style = MaterialTheme.typography.caption,
                fontSize = 11.sp
            )
        }
    }
}

@Preview
@Composable
fun testing() {
    MessageCard(chat = Chat("","Sahand", icon = "https://treenewal.com/wp-content/uploads/2020/11/oak-tree-care.png", content = "HELLo my name is sahand"), isSender = true )
}