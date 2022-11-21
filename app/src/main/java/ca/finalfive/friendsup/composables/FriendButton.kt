package ca.finalfive.friendsup.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R

/**
 * A button composable for the Friends List Screen
 * @param friendName represents the name that will be displayed on the button
 * @param onClick handle button click action
 */
@Composable
fun FriendButton(friendName: String, onClick: () -> Unit) {
    // The entire button size (with the darker color as the background)
    Card(
        // Darker part of the button
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick() },
        backgroundColor = colorResource(R.color.dark_purple),
        elevation = 10.dp
    ) {
        Row(modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
            // Text inside the button
            Text(
                text = friendName,
                color = Color.White,
                style = MaterialTheme.typography.h3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 21.sp,
                modifier = Modifier
                    .padding(start = 40.dp, bottom = 10.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.info_icon),
                contentDescription = "Info Icon",
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (-30).dp)
            )
        }
    }
    // Spacer between the options
    Spacer(modifier = Modifier
        .padding(bottom = 20.dp))
}