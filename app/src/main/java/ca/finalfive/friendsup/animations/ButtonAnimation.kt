package ca.finalfive.friendsup.animations

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.viewmodels.GameViewModel
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@Composable
fun ButtonAnimation(gameViewModel: GameViewModel, isQuestionAnswered: Boolean) {
    var isPlaying by remember {
        mutableStateOf(false)
    }

    // Variable for the composition
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.button_animation))

    val compResult = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.button_animation))

    // Variable of the Composition's progress to adjust the speed
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever
    )

    // Variable of the Composition's progress to adjust the speed
    val animationState = animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying
    )

    // When the animation ends
    LaunchedEffect(key1 = animationState.isAtEnd) {
        if (animationState.isAtEnd) {
            isPlaying = false
        }
    }

    // When the button gets clicked
    LaunchedEffect(key1 = gameViewModel.questionAnswered) {
        if (gameViewModel.questionAnswered) {
            isPlaying = true
        }
    }

    LottieAnimation(
        composition = composition,
        progress = { progress },
        contentScale = ContentScale.FillBounds
    )
}