package ca.finalfive.strangercommons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.composables.NavigationContainer
import ca.finalfive.strangercommons.screens.ChatRoomScreen
import ca.finalfive.strangercommons.screens.FriendsScreen
import ca.finalfive.strangercommons.screens.ProfileScreen
import ca.finalfive.strangercommons.screens.ReportScreen
import ca.finalfive.strangercommons.viewmodels.MyViewModel

/**
 * Screens for possible navigation
 * @param route Route of the screen
 */
sealed class Route(val route: String) {
    // Route to the game room screen
    object GameRoomScreen: Route("gameRoom")
    // Route to the friends screen
    object FriendsScreen: Route("friends")
    // Route to the profile screen
    object ProfileScreen: Route("profile")
    // Route to the report screen
    object ReportScreen: Route("report")
}

// https://medium.com/geekculture/bottom-navigation-in-jetpack-compose-android-9cd232a8b16
/**
 * Items for the bottom navigation
 * @param title Name of the screen
 * @param icon Icon id of the bottom navigation item
 * @param route Route of the screen
 */
sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    // Navigation item for the home screen, with the icon id and the route
    object Home : BottomNavItem("Games", R.drawable.message_icon, Route.GameRoomScreen.route)
    // Navigation item for the Friends screen, with the icon id and route
    object Friends : BottomNavItem("Friends", R.drawable.friend_icon, Route.FriendsScreen.route)
    // Navigation item for the Profile screen, with the icon id and the route
    object Profile : BottomNavItem("Profile", R.drawable.profile_icon, Route.ProfileScreen.route)
}

// navigation composable
@Composable
fun Navigation(viewModel: MyViewModel) {

    // Navigation controller
    val navController = rememberNavController()

    // Define navigation host an set the initial screen
    NavHost(navController = navController, startDestination = Route.GameRoomScreen.route) {

        // Navigation for the Game room screen
        composable(
            route = Route.GameRoomScreen.route
        ) {
            // game room screen with bottom navigation
            NavigationContainer(navController = navController) {
                ChatRoomScreen(navController = navController)
            }
        }

        // Navigation for the friends screen
        composable(
            route = Route.FriendsScreen.route,
        ) {
            // Friends Screen with bottom navigation
            NavigationContainer(navController = navController) {
                FriendsScreen(navController = navController)
            }
        }

        // Navigation for the profile screen
        composable(
            route = Route.ProfileScreen.route,
        ) {
            // Profile screen with bottom navigation
            NavigationContainer(navController = navController) {
                ProfileScreen(navController = navController)
            }
        }

        // Navigation for the report screen
        composable(
            route = Route.ReportScreen.route
        ) {
            ReportScreen(navController = navController)
        }
    }
}