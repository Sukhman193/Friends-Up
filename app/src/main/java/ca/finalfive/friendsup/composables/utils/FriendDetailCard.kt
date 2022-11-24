package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.models.FriendCardDetail
import kotlinx.coroutines.delay

// https://stackoverflow.com/questions/71985840/how-to-use-clipboard-service-in-jetpack-compose

/**
 * Friend detail card which will display the content of the
 * details of the user's social media
 * @param friendCardDetail content for the detail card
 */
@Composable
fun FriendDetailCard(friendCardDetail: FriendCardDetail) {
    // mutable value for the icon, set to the copy icon
    var trailingIcon by remember {
        mutableStateOf(R.drawable.copy_icon)
    }

    // Clipboard manager for the copy button
    val clipboardManager = LocalClipboardManager.current

    // Every time the icon changes
    // wait for 3s than turn the icon
    // back to the copy icon
    LaunchedEffect(key1 = trailingIcon) {
        delay(3000)
        //icon reference: Copy icon by Icons8 https://icons8.com/icon/set/copy/material-outlined
        trailingIcon = R.drawable.copy_icon
    }

    // Card of the Friend details
    Card (
        elevation = 9.dp,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        shape = RoundedCornerShape(10.dp)
    ){
        // Using text field because of it's alignments
        TextField(
            // content of the contact information name
            value = friendCardDetail.value,
            onValueChange = {},
            // text field cannot be modified
            readOnly = true,
            //  title of the contact information name
            label = {
                Text(
                    text = friendCardDetail.socialMedia,
                    color = colorResource(id = R.color.white)
                )
            },
            // Leading icon of the contact information name
            leadingIcon = {
                Icon(
                    painter = painterResource(id = friendCardDetail.icon),
                    contentDescription = "${friendCardDetail.socialMedia} Icon",
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier
                        .size(30.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.dark_purple)
            ),
            // Copy button to copy the content of the text
            trailingIcon = {
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        // Change the trailing icon to be a checkmark
                        trailingIcon = R.drawable.checkmark_icon
                        // Copy the text
                        clipboardManager.setText(AnnotatedString(friendCardDetail.value))
                    })
            },
            // fill the entire width of the container
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

