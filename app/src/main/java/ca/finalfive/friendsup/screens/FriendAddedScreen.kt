package ca.finalfive.friendsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.BackgroundImage
import ca.finalfive.friendsup.composables.ScreenTitle
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

/**
 * Screen which is displayed after two users
 * clicked on the add friends screen
 * @param navController Navigation controller of the application
 */
@Composable
fun FriendAddedScreen(navController: NavController) {
    // Composition for the animation
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.congratulation)
    )

    // Pop back a screen after one second
    LaunchedEffect(key1 = composition) {
        delay(3000)
        navController.popBackStack()
    }


    // Container of the screen
    Box {
        BackgroundImage(showMoon = true)
        // Content of the screen
        Column {
            // Add title to the screen
            ScreenTitle(title = R.string.add_friend_title)

            Spacer(modifier = Modifier.height(40.dp))

            // Congratulation Text
            Text(
                text = stringResource(id = R.string.friend_added_content),
                style = MaterialTheme.typography.h3,
                fontSize = 35.sp,
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 30.dp),
                )
            // Animation to display
            LottieAnimation(
                composition = composition,
                iterations = 1,
            )
        }
    }
}