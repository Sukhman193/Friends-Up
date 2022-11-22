package ca.finalfive.friendsup.composables.drawings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.models.GameQuestionOption

/**
 * Background container for coloring the Trivia questions
 * @param option current option being displayed
 * @param correctAnswer Correct answer to display the user
 */
@Composable
fun GameAnswerBackground(
    option: GameQuestionOption,
    correctAnswer: GameQuestionOption
) {
        // When display correct answer is set to true
        // correct answer must also be specified
        val bgColor = if (option.optionText == correctAnswer.optionText) {
            colorResource(id = R.color.saveGreen)
        } else if (option.selectedBy.isNotEmpty()) {
            colorResource(id = R.color.orange_trivia_wrong_question)
        } else {
            Color.Transparent
        }
        // Colored container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
        )
}