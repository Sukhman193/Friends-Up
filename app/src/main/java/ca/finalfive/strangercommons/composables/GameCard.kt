package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ca.finalfive.strangercommons.ui.theme.GameCardBackgroundColor
import ca.finalfive.strangercommons.ui.theme.StrangerCommonsTheme
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.ui.theme.comfortaa

@Composable
fun GameCard(){
    Card(
        modifier = Modifier
            .width(450.dp)
            .height(150.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = GameCardBackgroundColor,


    ){
        Row(modifier = Modifier.padding(10.dp)) {
            Column(modifier = Modifier
                .background(color = Color.Transparent)
                .width(300.dp)
            ) {
                DropShadowText(text = "Game Name", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(id = R.drawable.game_card_underline),
                    contentDescription = "underline",
                    modifier = Modifier.padding(start = 2.dp, top = 3.dp, bottom = 5.dp)
                )
                DropShadowText(text = "Game Description", fontSize = 10.sp)
            }
            Column(modifier = Modifier
                .background(color = Color.White)
                .width(40.dp)
            ) {
                Text(text = "hi")
            }
        }

    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    StrangerCommonsTheme {
        GameCard()
    }
}
