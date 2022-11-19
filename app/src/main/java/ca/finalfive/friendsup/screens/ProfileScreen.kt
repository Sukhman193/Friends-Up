package ca.finalfive.friendsup.screens

import androidx.compose.runtime.Composable
import ca.finalfive.friendsup.composables.ProfilePage

/**
 * Profile Screen - navigates to the Profile Page
 */
@Composable
<<<<<<< HEAD
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
=======
fun ProfileScreen() {
    ProfilePage()
>>>>>>> development
}