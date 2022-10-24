package ca.finalfive.strangercommons.screens

import androidx.compose.runtime.Composable
import ca.finalfive.strangercommons.composables.AuthPage
import ca.finalfive.strangercommons.viewmodels.AuthViewModel

@Composable
fun AuthScreen(authViewModel : AuthViewModel){
    AuthPage(authViewModel = authViewModel)
}