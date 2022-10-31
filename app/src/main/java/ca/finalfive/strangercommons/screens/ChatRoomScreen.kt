package ca.finalfive.strangercommons.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ca.finalfive.strangercommons.composables.GameCard

@Composable
fun ChatRoomScreen(navController: NavController) {
    Column {
        Text(text = "ChatRoom page")
        GameCard()
    }
}