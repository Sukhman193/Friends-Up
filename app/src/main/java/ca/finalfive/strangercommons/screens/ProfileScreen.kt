package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.BackgroundImage

@Composable
fun ProfileScreen(navController: NavController) {
    Column() {
        Text(text = "Profile page")
    }
}