package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R

//a composable message that will appear at the bottom of the tapped option
//reference: https://stackoverflow.com/questions/73333287/how-to-show-a-composable-just-for-e-few-seconds
@Composable
fun TimedTapMessage(){
    //displaying the onTap message
    Row(){
        Text(
            color = colorResource(id = R.color.grey_font),
            fontSize = 12.sp,
            text = "Double tap to confirm option selection!"
        )
    }
}