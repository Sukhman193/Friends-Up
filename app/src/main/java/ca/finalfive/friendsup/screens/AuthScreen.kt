package ca.finalfive.friendsup.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.friendsup.composables.AuthPage
import ca.finalfive.friendsup.viewmodels.AuthViewModel

/**
 * Auth Screen - redirects to auth page
 * @param authViewModel - The AuthViewModel Instance
 * @param navController - The Nav-Controller to navigate through screens
 */
@Composable
fun AuthScreen(authViewModel : AuthViewModel, navController: NavController){
    // Routing to Authentication Page
    AuthPage(authViewModel = authViewModel, navController = navController)
}