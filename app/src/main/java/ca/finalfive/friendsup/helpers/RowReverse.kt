package ca.finalfive.friendsup.helpers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

// https://stackoverflow.com/questions/66835210/change-layout-direction-of-a-composable
@Composable
        /**
         * Row layout component with a reverse functionality
         * @param modifier Modifier for the row
         * @param horizontalArrangement Arrangement for the row
         * @param verticalAlignment Alignment for the row
         * @param reversed States whether the row should be reversed or not
         * @param content Content of the row
         */
fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    reversed: Boolean,
    content: @Composable RowScope.() -> Unit,
) {
    // Get the layout direction for the row
    val direction = if(reversed) LayoutDirection.Rtl else LayoutDirection.Ltr
    // Change the composition's layout direction
    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        androidx.compose.foundation.layout.Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            // Display the content of the row
            content()
        }
    }
}