package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.showMoon

@Composable
fun FriendsScreen(navController: NavController) {
    showMoon = true

    Column() {
        Text(text = "Friend page")
    }
}