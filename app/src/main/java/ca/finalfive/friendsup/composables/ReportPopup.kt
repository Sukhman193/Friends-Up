package ca.finalfive.friendsup.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.navigation.Route
import ca.finalfive.friendsup.viewmodels.GameViewModel

@Composable
        /**
         * Report popup for opening a popup page
         * @param setReportPopup function which sets the report popup to change
         * @param gameViewModel view model for the games
         */
fun ReportPopup(
    setReportPopup: (Boolean) -> Unit,
    gameViewModel: GameViewModel
) {
    // Dark background to cover the entire screen
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.3f))
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        setReportPopup(false)
                    }
                )
            },
    ) {
        // Container for the background
        Card(
            modifier = Modifier
                .width(308.dp)
                    // The following tap gesture is added
                    // because otherwise, clicking on the
                    // card would close it, but instead
                    // we want to close the popup
                    // only when the user clicks outside
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {}
                    )
                },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(10.dp)
        ) {
            // Column for the texts
            Column(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Report popup title
                Text(
                    text = stringResource(id = R.string.report_popup_title),
                    color = Color.Black,
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.body1
                )
                // Report popup description
                Text(
                    text = stringResource(id = R.string.report_popup_description),
                    color = Color.Black,
                    fontSize = 11.sp
                )
                // Report popup note
                Text(
                    text = stringResource(id = R.string.report_popup_note),
                    color = Color.Black,
                    fontSize = 9.sp
                )
                // Report button and cancel button
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                    // Report button
                    Text(
                        text = stringResource(id = R.string.button_report),
                        color = MaterialTheme.colors.error,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp).clickable {
                           gameViewModel.isReportScreenOpened = true
                        }
                    )
                    // Cancel button
                    Text(
                        text = stringResource(id = R.string.button_cancel),
                        color = Color.Black,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .clickable {
                                setReportPopup(false)
                            }
                    )
                }
            }
        }
    }
}