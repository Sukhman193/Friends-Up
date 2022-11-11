package ca.finalfive.strangercommons.composables.drawings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
        /**
         * Line separator for the screen, it adds a small
         * padding to the top of the line
         */
fun LineSeparator() {
    // Container of the application
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Add top padding to the line
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(20.dp)
                    .background(Color.White))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .widthIn(255.dp)
                    .background(Color.White))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(20.dp)
                    .background(Color.White))
        }
    }
}