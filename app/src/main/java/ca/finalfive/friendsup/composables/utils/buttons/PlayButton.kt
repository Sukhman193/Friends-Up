package ca.finalfive.friendsup.composables.utils.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.utils.DropShadowIcon
import ca.finalfive.friendsup.composables.utils.DropShadowText
import ca.finalfive.friendsup.navigation.Route
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * Play button for the select game screen
 * @param gameViewModel view model for the game
 * @param navController Navigation controller for the application
 * @param gameMode game Mode being played
 */
@Composable
fun PlayButton(gameViewModel: GameViewModel, navController: NavController, gameMode: String) {
    //this component makes the play button that appears on the main game screen
    Button(modifier = Modifier
        .background(shape = RoundedCornerShape(8.dp), color = Color.Transparent)
        .padding(top = 10.dp, bottom = 10.dp)
        .width(100.dp)
        .shadow(
            elevation = 10.dp,
            ambientColor = colorResource(id = R.color.black),
            shape = RoundedCornerShape(8.dp)
        ),
        //when the button is clicked, we will send the user to the correct game that's passed in
        onClick = {
            gameViewModel.joinGame(gameMode = gameMode)
            navController.navigate(Route.GameScreen.route)
        }
    ) {
        //this is the inside of the whole button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //custom text icons with a shadow
            DropShadowIcon(Icon = R.drawable.ic_play_arrow)
            //custom text box with a shadow
            DropShadowText(
                text = "Play",
                fontSize = 10.sp,
            )
        }
    }
}
