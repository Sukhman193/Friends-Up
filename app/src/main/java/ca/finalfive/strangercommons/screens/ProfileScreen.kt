package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.ProfilePage
import ca.finalfive.strangercommons.viewmodels.AuthViewModel
import ca.finalfive.strangercommons.viewmodels.UserViewModel

@Composable
fun ProfileScreen(userViewModel: UserViewModel, authViewModel: AuthViewModel) {
    ProfilePage(userViewModel = userViewModel, authViewModel = authViewModel)
}