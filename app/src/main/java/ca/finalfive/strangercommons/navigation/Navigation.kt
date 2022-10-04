package ca.finalfive.strangercommons.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.finalfive.strangercommons.composables.BottomNavigation
import ca.finalfive.strangercommons.screens.ChatRoomScreen
import ca.finalfive.strangercommons.screens.FriendsScreen
import ca.finalfive.strangercommons.screens.ProfileScreen
import ca.finalfive.strangercommons.viewmodels.MyViewModel

/**
 * Screens for possible navigation
 */
sealed class Route(val route: String) {
    object ChatRoomScreen: Route("chatRoom")
    object FriendsScreen: Route("friends")
    object ProfileScreen: Route("profile")
}

// navigation composable
@Composable
fun Navigation(viewModel: MyViewModel) {

    // Navigation controller
    val navController = rememberNavController()

    // Define navigation host an set the initial screen
    NavHost(navController = navController, startDestination = Route.ChatRoomScreen.route) {

        composable(Route.ChatRoomScreen.route) {
            //  The bottom bar is set up here for now, since some pages will not require the navigation
            Scaffold(
                bottomBar = { BottomNavigation(navController = navController) }
            ) {
                // Temp page
                ChatRoomScreen()
            }
        }

        composable(
            route = Route.FriendsScreen.route,
        ) {
            Scaffold(
                bottomBar = { BottomNavigation(navController = navController) }
            ) {
                // Temp Page
                FriendsScreen()
            }
        }

        composable(
            route = Route.ProfileScreen.route,
        ) {
            Scaffold(
                bottomBar = { BottomNavigation(navController = navController) }
            ) {
                // Temp Page
                ProfileScreen()
            }
        }
    }
}