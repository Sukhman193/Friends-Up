package ca.finalfive.friendsup.navigation

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
import ca.finalfive.friendsup.screens.games.TriviaGameScreen
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
                ProfileScreen()
            }
        }

        // Navigation for the report screen
        composable(
            route = Route.ReportScreen.route
        ) {
           
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
            /**
             * Route of the current screen
             */
            val currentRoute = navController.currentBackStackEntryAsState()
                .value?.destination?.route
            // If the game has not been created, pop back one screen
            // This is needed for the screen when the user leaves the
            // application
            if(!gameViewModel.createGameRoomCalled) {
                // Check if the current route
                if(currentRoute == Route.QueueScreen.route)
                    // Go back to the game selection screen
                    navController.popBackStack()
            }

            // This statement will handle removing the user from the database
            // Whenever the user clicks on the back button while they are either
            // waiting in the queue, or in a game
            if(currentRoute != Route.QueueScreen.route && currentRoute != null) {
                // Check if the game has been created or not
                if(gameViewModel.game != null) {
                    // Remove the game
                    gameViewModel.removeUserFromGame()
                }
            }

            // If the user has clicked on report user
            // open the report user screen
            if(gameViewModel.isReportScreenOpened) {
                // Report screen display
                ReportScreen(
                    navController = navController,
                    gameViewModel = gameViewModel
                )
            // If the user is adding the user as a friend and the game has currently
            // all the members inside than display the add as queue screen
            } else if (gameViewModel.isAddAsFriendScreenOpened && 
                    gameViewModel.game?.members?.size == gameViewModel.game?.maxMembers) {
                // TODO: Add the queue system for the add friend
                // If both the friends have added each other show the screen for users added
                if(gameViewModel.game?.addFriendList?.size == gameViewModel.game?.maxMembers) {
                    // TODO: Add screen for both users are friends
                    // TODO: Add animation for this screen, it will look nice
                    // TODO: Add 5 seconds timer to pop back a screen
                    Text(text = "YOU are now friends")
                } else {
                    // Display the queue for adding a user as a friend
                    AddFriendQueueScreen(gameViewModel = gameViewModel)
                }
            // If the game has started and not ended and all the members are in the game
            // than display the game screen a
            } else if (gameViewModel.game?.gameStarted == true &&
                    gameViewModel.game?.members?.size == gameViewModel.game?.maxMembers &&
                    gameViewModel.game?.gameEnded == false) {
                // Check which game they are playing
                when(gameViewModel.game?.gameMode) {
                    // Playing trivia game
                    GameMode.TRIVIA -> {
                        // TODO: add screen for Trivia game
                        TriviaGameScreen(gameViewModel)
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
            // If the game has ended or a user leaves in the middle of the game display the 
            // end game screen
            } else if(gameViewModel.game?.gameEnded == true ||
                (
                gameViewModel.game?.gameStarted == true &&
                gameViewModel.game?.members?.size != gameViewModel.game?.maxMembers
                )
            ) {
                // TODO: ADD END GAME SCREEN HERE
                Text(text = "END game :) :) :) :)")
            // If none of the above are true than display the game queue screen
            } else {
                // There is a bug in which when the user goes back from the game screen
                // The game queue screen will show up for a split second
                // so we check if the current screen route matches the game screen route
                // Reason is that there is a time when the current route is null
                if(currentRoute == Route.QueueScreen.route) {
                    GameQueueScreen(navController = navController)
                }
            }
        }
    }
}