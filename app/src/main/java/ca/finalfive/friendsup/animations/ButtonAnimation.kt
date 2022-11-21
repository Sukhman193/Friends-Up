package ca.finalfive.friendsup.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import ca.finalfive.friendsup.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ButtonAnimation() {

    // Variable for the composition
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.button_animation))


    // Variable of the Composition's progress to adjust the speed
    val progress by animateLottieCompositionAsState(
        composition,
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        contentScale = ContentScale.FillBounds
    )
}