package ca.finalfive.friendsup.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import ca.finalfive.friendsup.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * The animation of the circle timer (not including the number inside)
 * @param length The length of the timer in seconds
 */
@Composable
fun TimerAnimation(length: Float) {
    // Variable for the composition
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.circle_timer))
    // Variable of the Composition's progress to adjust the speed
    val progress by animateLottieCompositionAsState(
        composition,
        speed = 15F / length
    )

    // Start the animation
    LottieAnimation(
        composition = composition,
        progress = { progress }
    )
}