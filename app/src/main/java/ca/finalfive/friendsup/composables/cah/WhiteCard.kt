package ca.finalfive.friendsup.composables.cah

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.utils.ProfileIcon
import ca.finalfive.friendsup.composables.utils.TimedTapMessage
import ca.finalfive.friendsup.models.Game
import ca.finalfive.friendsup.models.GameQuestionOption
import ca.finalfive.friendsup.viewmodels.GameViewModel
import kotlinx.coroutines.delay


/**
 * White card for the CAH game
 * @param gameViewModel game View model of the application
 * @param align alignment of the card
 * @param whiteCard white card to be displayed
 * @param game game to be displayed
 */
@Composable
fun WhiteCard(
    gameViewModel: GameViewModel,
    align: Alignment,
    whiteCard: GameQuestionOption,
    game: Game
) {

    var isTapped by remember {
        mutableStateOf(false)
    }

    //reference: https://stackoverflow.com/questions/73333287/how-to-show-a-composable-just-for-e-few-seconds
    //starts as soon as the option is tapped
    LaunchedEffect(key1 = isTapped) {
        if (!isTapped) return@LaunchedEffect
        //delaying this action by 3 seconds allowing the message to show
        delay(3000)
        isTapped = false
    }
    // current context of the application
    val context = LocalContext.current
    // Container for the white card
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // White card
        Card(
            modifier = Modifier
                .height(175.dp)
                .width(135.dp)
                .align(align)
                //.clickable {}
                // On double click the card is selected
                .pointerInput(whiteCard) {
                    detectTapGestures(
                        onTap = {
                            isTapped = true
                        },
                        onDoubleTap = {
                            gameViewModel.handleAnswerGameOption(
                                gameOption = whiteCard,
                                context = context
                            )
                            isTapped = false
                        }
                    )
                },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = colorResource(id = R.color.white),
            elevation = 10.dp,
        ) {
            // Using scaffold so that we can show
            // the icons at the bottom
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    if (isTapped){
                        TimedTapMessage(location = "CAH")
                    } else {
                        Row(modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 10.dp)
                        ) {
                            // iterate over the selected by list
                            whiteCard.selectedBy.forEach { selectedBy ->
                                // iterate over the game members list
                                game.members.forEach {
                                    // display the profile icon of the user
                                    // who selected the game
                                    if (it.username == selectedBy) {
                                        ProfileIcon(imageUrl = it.icon)
                                    }
                                }
                            }
                        }
                    }

                }
            ) { paddingValued ->
                // Display image of user who selected this option
                Text(
                    text = whiteCard.optionText,
                    modifier = Modifier
                        .padding(paddingValues = paddingValued)
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState()),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
        }
    }
}