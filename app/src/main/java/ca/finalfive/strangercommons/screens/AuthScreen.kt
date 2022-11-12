package ca.finalfive.strangercommons.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.AuthPage
import ca.finalfive.strangercommons.navigation.Route
import ca.finalfive.strangercommons.viewmodels.AuthViewModel
import ca.finalfive.strangercommons.viewmodels.UserViewModel

/**
 * Auth Screen - redirects to auth page
 * @param authViewModel - The AuthViewModel Instance
 * @param navController - The Nav-Controller to navigate through screens
 */
@Composable
fun AuthScreen(authViewModel : AuthViewModel, navController: NavController, userViewModel: UserViewModel){
    // Routing to Authentication Page
    AuthPage(authViewModel = authViewModel, navController = navController, userViewModel = userViewModel)
}