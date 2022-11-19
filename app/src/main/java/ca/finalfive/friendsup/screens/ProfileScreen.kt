package ca.finalfive.friendsup.screens

import androidx.compose.runtime.Composable
import ca.finalfive.friendsup.composables.ProfilePage
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.UserViewModel

/**
 * Profile Screen - navigates to the Profile Page
 */
@Composable
fun ProfileScreen(userViewModel: UserViewModel) {
    ProfilePage(userViewModel = userViewModel)
}