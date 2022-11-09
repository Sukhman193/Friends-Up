package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.GameTimer
import ca.finalfive.strangercommons.composables.showMoon
import ca.finalfive.strangercommons.ui.theme.comfortaa
import ca.finalfive.strangercommons.ui.theme.hello_valentina

// Color for the top container
val containerColor = Color(0xFF3728A5).copy(alpha = 0.2F)

var currentQuestion: Int by mutableStateOf(1)
var totalQuestions: Int by mutableStateOf(5)

@Composable
fun TriviaGameScreen(navController: NavController) {
    // Disable the moon from showing
    showMoon = false

    // Black background on top to make the main background dimmer
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5F))
    )

    // Column containing all the elements you currently see
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        // Container at the top which includes the title of the mini-game
        MaterialTheme(typography = Typography(defaultFontFamily = hello_valentina)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(containerColor)
            ) {
                Text(text = "Trivia",
                    color = Color.White,
                    fontSize = 60.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 30.dp)
                )
            }
        }

        Spacer(modifier = Modifier
            .padding(bottom = 20.dp))

        // Component containing the timer animation along with the lines above it,
        // and the words to the left of the timer
        GameTimer(totalTime = 30, prompt = "Question", currentQuestion = currentQuestion, totalQuestions = totalQuestions)

        // Question for the user
        MaterialTheme(typography = Typography(defaultFontFamily = comfortaa)) {
            Text(
                text = "Which one of these is a fruit?",
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
                    .padding(top = 0.dp)
            )
        }
    }
}