package ca.finalfive.friendsup.screens

import androidx.compose.runtime.Composable
import ca.finalfive.friendsup.composables.ProfilePage
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.UserViewModel

@Composable
/**
 * Profile Screen - navigates to the Profile Page
 */
fun ProfileScreen(userViewModel: UserViewModel, authViewModel: AuthViewModel) {
    ProfilePage(userViewModel = userViewModel, authViewModel = authViewModel)
}