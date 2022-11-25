package ca.finalfive.friendsup.composables.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R

//a composable message that will appear at the bottom of the tapped option
//reference: https://stackoverflow.com/questions/73333287/how-to-show-a-composable-just-for-e-few-seconds
@Composable
fun TimedTapMessage(location: String){
    //displaying the onTap message
    Row(){
        //if this is displayed in the cards against humanity game, we want to make the text different
        if (location == "CAH"){
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                color = colorResource(id = R.color.dark_grey_font),
                fontSize = 12.sp,
                text = "Double tap to confirm option selection!"
            )
        } else {
            //this is when it displays anywhere else besides cards against humanity
            Text(
                color = colorResource(id = R.color.grey_font),
                fontSize = 12.sp,
                text = "Double tap to confirm option selection!"
            )
        }
    }
}