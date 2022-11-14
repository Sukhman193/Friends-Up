package ca.finalfive.friendsup.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.NavigationContainer
import ca.finalfive.friendsup.models.GameMode
import ca.finalfive.friendsup.screens.*
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.GameViewModel

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
    // Route to the Authentication screen
    object AuthScreen: Route("auth")
    // Route for the queue screen
    object QueueScreen: Route("Queue")
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
fun Navigation(gameViewModel: GameViewModel, authViewModel: AuthViewModel) {

    // Navigation controller
    val navController = rememberNavController()

    // User Authenticated or Not
    val startingScreen: String =
        if (authViewModel.user == null) {
            Route.AuthScreen.route
        } else {
            Route.GameRoomScreen.route
        }

    // Define navigation host an set the initial screen
    NavHost(
        navController = navController,
        startDestination = startingScreen
    ) {

        // Navigation for the Game room screen
        composable(
            route = Route.GameRoomScreen.route
        ) {
            // game room screen with bottom navigation
            NavigationContainer(navController = navController) {
                GameRoomScreen(
                    navController = navController,
                    gameViewModel = gameViewModel
                )
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

        // Navigation for the authentication screen
        composable(
            route = Route.AuthScreen.route,
        ) {
            // Auth Screen with authViewModel
            AuthScreen(
                authViewModel = authViewModel,
                navController = navController,
                gameViewModel = gameViewModel
            )
        }

        // In game composable
        // It will handle the different games content
        composable(
            route = Route.QueueScreen.route
        ) {
            // If the game has not been created, pop back one screen
            if(!gameViewModel.createGameRoomCalled) {
                if(navController.currentBackStackEntryAsState()
                        .value?.destination?.route == Route.QueueScreen.route)
                    navController.popBackStack()
            }
            // This statement will handle removing the user from the database
            // Whenever the user clicks on the back button while they are either
            // waiting in the queue, on in a game
            if(navController.currentBackStackEntryAsState()
                    .value?.destination?.route != Route.QueueScreen.route) {
                // Check if the game has been created or not
                if(gameViewModel.game != null) {
                    // Remove the game
                    gameViewModel.removeUserFromGame()
                }
            }
            // Check whether the game has started and there are two users
            // a user might leave right when he joins, so we need to make sure
            // both users are present
            // In this case I have a maxMembers value saved in the database
            // so that in future updates we can make multiple people join at the same time
            if(gameViewModel.game?.gameStarted == true &&
                gameViewModel.game?.members?.size == gameViewModel.game?.maxMembers) {
                // Check which game they are playing
                when(gameViewModel.game?.gameMode) {
                    // Playing trivia game
                    GameMode.TRIVIA -> {
                        // TODO: add screen for Trivia game
                        Column {
                            Text(
                                text = "TRIVIA Game Started",
                                style = MaterialTheme.typography.h1)
                            Button(onClick = { gameViewModel.sendMessage("EHLLLOOOOO") }) {
                                Text(text = "SEND MESSAGE")
                            }
                        }
                        
                    }
                    // Playing Prompt game
                    GameMode.PROMPT -> {
                        // TODO: add screen for Prompt game
                        Text(
                            text = "PROMPT Game Started",
                            style = MaterialTheme.typography.h1)
                    }
                    // Playing Would you rather game
                    GameMode.WOULD_YOU_RATHER -> {
                        // TODO: add screen for would you rather
                        Text(
                            text = "WOULD YOU RATHER Game Started",
                            style = MaterialTheme.typography.h1)
                    }
                    // Playing Cards against humanity game
                    GameMode.CARDS_AGAINST_HUMANITY -> {
                        // TODO: add screen for cards against humanity
                        Text(
                            text = "Cards against humanity Game Started",
                            style = MaterialTheme.typography.h1)
                    }
                }
                // Handle game started but the other user decided to leave
            } else if(gameViewModel.game?.gameStarted == true) {
                // TODO: Handle when the game started but a user decided to leave
                // Suggestion is to navigate to the game ended screen
            } else {
                // Display queue screen
                GameQueueScreen(navController = navController)
            }
        }
    }
}