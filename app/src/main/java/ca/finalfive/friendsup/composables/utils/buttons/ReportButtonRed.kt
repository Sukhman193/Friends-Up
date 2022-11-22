package ca.finalfive.friendsup.composables.utils.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.finalfive.friendsup.R

/**
 * Red Report button, used inside the end game screen
 * @param action Action taken by the button click
 */
@Composable
fun ReportButtonRed(
    action: () -> Unit
) {
    // The card is going to be used as a button
    Card(
        elevation = 9.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(200.dp)
            .clickable { action() },
        backgroundColor = colorResource(id = R.color.cancelRed)
    ) {
        // Put the text and icon in a row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Report text of the button
            Text(
                text = stringResource(id = R.string.button_report_user),
                fontSize = 12.sp,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold
            )
            // Flag icon for the report button
            Icon(
                painter = painterResource(id = R.drawable.flag),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(18.dp),
            )
        }
    }
}