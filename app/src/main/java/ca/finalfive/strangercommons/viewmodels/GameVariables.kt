package ca.finalfive.strangercommons.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

// Color for the top container
val containerColor = Color(0xFF3728A5).copy(alpha = 0.2F)
// Colors for the button choices
val buttonColorLight = Color(0xFF5C56DE)
val buttonColorDark = Color(0xFF47458F)
// Color for the TextField
val textFieldColor = Color(0xFFD9D9D9)

// Keeps track of what question the user is on
var currentQuestion: Int by mutableStateOf(1)
// The total amount of questions in the game, made it a mutableState
// just in case we would ever need to change it
var totalQuestions: Int by mutableStateOf(5)
// The value for the denominator in finding the decimal value for
// a single bar
var offsetValue: Float by mutableStateOf(1f)