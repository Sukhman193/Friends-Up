package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.ui.theme.StrangerCommonsTheme

@Composable
fun playButton(){
    Button(modifier = Modifier
        .background(shape = RoundedCornerShape(8.dp), color = Color.Transparent)
        .padding(top = 10.dp, bottom = 10.dp)
        .width(100.dp)
        .shadow(
            elevation = 10.dp,
            ambientColor = colorResource(id = R.color.black),
            shape = RoundedCornerShape(8.dp)
        ),
        onClick = { /*TODO*/ }) {
        Column(modifier = Modifier.padding(top = 5.dp).width(100.dp)) {
            DropShadowIcon(Icon = R.drawable.ic_play_arrow)

            DropShadowText(text = "Play",
                fontSize = 10.sp,
            )


        }
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview1() {
    StrangerCommonsTheme {
        playButton()
    }
}