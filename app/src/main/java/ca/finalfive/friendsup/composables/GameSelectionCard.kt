package ca.finalfive.friendsup.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.ui.theme.GameCardBackgroundColor
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.buttons.playButton
import ca.finalfive.friendsup.viewmodels.GameViewModel


@Composable
fun GameSelectionCard(gameName: String, gameDesc: String, gameViewModel: GameViewModel, navController: NavController){
    Card(
        modifier = Modifier
            .width(470.dp)
            .height(120.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = GameCardBackgroundColor,
        elevation = 10.dp
    ){
        Row(modifier = Modifier.padding(10.dp)) {
            Column(modifier = Modifier
                .background(color = Color.Transparent)
                .width(275.dp)
            ) {
                DropShadowText(text = gameName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(id = R.drawable.game_card_underline),
                    contentDescription = "underline",
                    modifier = Modifier.padding(start = 2.dp, top = 3.dp, bottom = 5.dp)
                )
                DropShadowText(text = gameDesc, fontSize = 12.sp, color = colorResource(id = R.color.gameCardDescFont))
            }
            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                playButton(gameViewModel, navController)
            }
        }

    }
}

