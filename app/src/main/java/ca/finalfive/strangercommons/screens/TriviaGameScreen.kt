package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.composables.GameTimer
import ca.finalfive.strangercommons.composables.showMoon
import ca.finalfive.strangercommons.ui.theme.comfortaa
import ca.finalfive.strangercommons.ui.theme.hello_valentina

// Color for the top container
val containerColor = Color(0xFF3728A5).copy(alpha = 0.2F)
// Color for the button choices
val buttonColorLight = Color(0xFF5C56DE)
val buttonColorDark = Color(0xFF47458F)

var currentQuestion: Int by mutableStateOf(1)
var totalQuestions: Int by mutableStateOf(5)

@Composable
fun TriviaGameScreen(navController: NavController) {
    // Disable the moon from showing
    showMoon = false

    val answers = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")

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
                // Background box behind "Trivia" and the Flag icon
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(containerColor)
                ) {
                    // Trivia text on top
                    Text(
                        text = "Trivia",
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
        GameTimer(totalTime = 30, prompt = "Question", currentQuestion = currentQuestion, totalQuestions = totalQuestions)

        // Question for the user
        MaterialTheme(typography = Typography(defaultFontFamily = comfortaa)) {
            // Displayed Question
            Text(
                text = "Which one of these is a fruit?",
                color = Color.White,
                style = MaterialTheme.typography.h3,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
                    .padding(top = 0.dp)
            )
            
            Spacer(modifier = Modifier
                .padding(bottom = 30.dp))

            // A for loop containing 4 button options
            for (answer in answers) {
                // The entire button size (with the darker color as the background)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(70.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(buttonColorDark)
                ) {
                    // Lighter color on the left of the button
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp)
                            .background(buttonColorLight)
                    )

                    // Text inside the button
                    Text(
                        text = "$answer",
                        color = Color.White,
                        style = MaterialTheme.typography.h3,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 65.dp)
                            .padding(bottom = 10.dp)
                    )
                }
                // Spacer between the options
                Spacer(modifier = Modifier
                    .padding(bottom = 12.dp))
            }
            // Confirm button
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(buttonColorLight)) {
                // Text inside the button
                Text(
                    text = "Confirm",
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