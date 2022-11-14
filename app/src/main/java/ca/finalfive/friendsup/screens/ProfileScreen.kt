package ca.finalfive.friendsup.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ca.finalfive.friendsup.composables.ReportPopup

@Composable
fun ProfileScreen(navController: NavController) {
    val (openReportPopup, setReportPopup) = remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Text(
                text = "Profile page",
                modifier = Modifier.clickable {
                    setReportPopup(true)
                }
            )
        }
        if(openReportPopup) {
//            ReportPopup(setReportPopup = setReportPopup)
        }
    }
}