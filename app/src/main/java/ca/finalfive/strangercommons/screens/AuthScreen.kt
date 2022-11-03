package ca.finalfive.strangercommons.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.AuthPage
import ca.finalfive.strangercommons.navigation.Route
import ca.finalfive.strangercommons.viewmodels.AuthViewModel

@Composable
fun AuthScreen(authViewModel : AuthViewModel, navController: NavController){
    if(authViewModel.user != null) {
        navController.navigate(Route.GameRoomScreen.route)
    }
    AuthPage(authViewModel = authViewModel, navController = navController)
}