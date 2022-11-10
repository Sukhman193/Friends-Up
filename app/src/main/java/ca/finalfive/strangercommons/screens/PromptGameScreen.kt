package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.*
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.composables.GameTimer
import ca.finalfive.strangercommons.composables.showMoon
import ca.finalfive.strangercommons.ui.theme.comfortaa
import ca.finalfive.strangercommons.ui.theme.hello_valentina

@Composable
fun PromptGameScreen(navController: NavController) {
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
            Row() {
                // Background box behind "Prompt" and the Flag icon
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(containerColor)
                ) {
                    // Prompt text on top
                    Text(
                        text = "Prompt",
                        color = Color.White,
                        fontSize = 60.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 30.dp)
                    )
                    // Flag icon / Report button
                    Image(
                        painter = painterResource(id = R.drawable.flag),
                        contentDescription = "Report Button",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(65.dp)
                            .padding(end = 30.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier
            .padding(bottom = 20.dp))

        // Component containing the timer animation along with the lines above it,
        // and the words to the left of the timer
        GameTimer(totalTime = 30, prompt = "Prompt", currentQuestion = currentQuestion, totalQuestions = totalQuestions)

        // Question for the user
        MaterialTheme(typography = Typography(defaultFontFamily = comfortaa)) {
            // Displayed Question
            Text(
                text = "Why is the Earth not flat?",
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier
                .padding(bottom = 10.dp))

            // Skip button
            Box(modifier = Modifier
                .padding(end = 30.dp)
                .height(45.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(buttonColorLight)
                .align(Alignment.End)) {
                // Text inside the button
                Text(
                    text = "Skip >",
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontSize = 19.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 5.dp)
                )
            }
        }
    }
}