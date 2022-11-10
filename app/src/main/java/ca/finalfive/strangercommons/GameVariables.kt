package ca.finalfive.strangercommons

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

// Color for the top container
val containerColor = Color(0xFF3728A5).copy(alpha = 0.2F)
// Color for the button choices
val buttonColorLight = Color(0xFF5C56DE)
val buttonColorDark = Color(0xFF47458F)

var currentQuestion: Int by mutableStateOf(1)
var totalQuestions: Int by mutableStateOf(5)