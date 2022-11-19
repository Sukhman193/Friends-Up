package ca.finalfive.friendsup.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R

//reference https://medium.com/tech-takeaways/jetpack-compose-drop-shadow-text-effect-b2f95d0dc2b5
@Composable
fun DropShadowText(
    //we can override whatever we need to change to match the text, but we also set some defaults
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = colorResource(id = R.color.white)
) {
    Box() {
        //this creates the shadow
        Text(
            modifier = modifier
                .alpha(alpha = 0.25f)
                .offset(x = 1.dp, y = 2.dp)
                .blur(radius = 2.dp),
            color = colorResource(id = R.color.black),
            fontSize = fontSize,
            text = text
        )
        //this displays the actual text
        Text(
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            text = text,
            fontWeight = fontWeight,

            )
    }
}

//this is the same thing, but for any icons we make!
@Composable
fun DropShadowIcon(
    //we can override whatever we need to change to match the text
    modifier: Modifier = Modifier,
    Icon: Int,
) {
    //using a box so we can layer the two icons on top of each other
    Box() {
        //this creates the 'shadow'
        Icon(
            painter = painterResource(id = Icon),
            contentDescription = "icon shadow",
            modifier = modifier
                .alpha(alpha = 0.25f)
                .offset(x = 1.dp, y = 2.dp)
                .blur(radius = 2.dp),
            colorResource(id = R.color.black)
        )
        //this displays the actual icon
        Icon(
            painter = painterResource(id = Icon),
            contentDescription = "icon",
            modifier = modifier,
        )
    }
}