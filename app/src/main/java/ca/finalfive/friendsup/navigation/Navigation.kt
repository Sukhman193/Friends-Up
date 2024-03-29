package ca.finalfive.friendsup.navigation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.composables.navigation.NavigationContainer
import ca.finalfive.friendsup.factories.AuthViewModelFactory
import ca.finalfive.friendsup.factories.UserViewModelFactory
import ca.finalfive.friendsup.repositories.FirebaseAuthRepository
import ca.finalfive.friendsup.repositories.FirestoreUserRepository
import ca.finalfive.friendsup.screens.*
import ca.finalfive.friendsup.screens.games.GameNavigator
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.GameViewModel
import ca.finalfive.friendsup.viewmodels.UserViewModel

/**
 * Arguments for the dynamic routing
 */
class RouteArgs {
    companion object {
        const val USER_ID = "userID"
    }
}

/**
 * Screens for possible navigation
 * @param route Route of the screen
 */
sealed class Route(val route: String) {
    // Route to the game room screen
    object GameRoomScreen : Route("gameRoom")

    // Route to the friends screen
    object FriendsScreen : Route("friends")

    // Route to the profile screen
    object ProfileScreen : Route("profile")

    // Route to the report screen
    object ReportScreen : Route("report")

    // Route to the Authentication screen
    object AuthScreen : Route("auth")

    // Route for the queue screen
    object GameScreen : Route("gameScreen")
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
    //icon reference: Game Controller icon by Icons8 https://icons8.com/icon/set/user/material-outlined
    object Home : BottomNavItem("Games", R.drawable.game_icon, Route.GameRoomScreen.route)

    // Navigation item for the Friends screen, with the icon id and route
    //icon reference: User Account icon by Icons8 https://icons8.com/icon/set/user/material-outlined
    object Friends : BottomNavItem("Friends", R.drawable.friend_icon, Route.FriendsScreen.route)

    // Navigation item for the Profile screen, with the icon id and the route
    //icon reference: User icon by Icons8 https://icons8.com/icon/set/user/material-outlined
    object Profile : BottomNavItem("Profile", R.drawable.profile_icon, Route.ProfileScreen.route)
}

// navigation composable
@Composable
fun Navigation(
    gameViewModel: GameViewModel,
    authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(FirebaseAuthRepository())),
    userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(FirestoreUserRepository()))
) {

    val context: Context = LocalContext.current
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
            // User must always be authenticated
            if (authViewModel.user == null) {
                navController.popBackStack()
            }
            // get the user information
            userViewModel.getUser(
                authViewModel.user!!.email!!.replace("@gmail.com", ""),
                context = context
            )
            // if the user is null display a progress bar
            if (userViewModel.user == null) {
                NavigationContainer(navController = navController) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(90.dp)
                    )
                }

            } else {
                // Friends Screen with bottom navigation
                NavigationContainer(navController = navController) {
                    FriendsScreen(
                        navController = navController,
                        friends = userViewModel.user!!.friendList
                    )
                }
            }
        }

        // Navigation for the friends detail screen
        composable(
            route = Route.FriendsScreen.route + "/{${RouteArgs.USER_ID}}",
            arguments = listOf(
                navArgument(RouteArgs.USER_ID) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            // Get the user id of the friend
            val userID = navBackStackEntry.arguments?.getString(RouteArgs.USER_ID)
            // If the user id is null, than go back one screen
            if (userID == null) {
                navController.popBackStack()
            }
            val friendID = userID!!
            // Get the friend from the user
            userViewModel.findFriendById(
                userId = friendID,
                context = context
            )
            // Get the friend
            val friend = userViewModel.friend
            // Check if the friend has been found or not
            val friendFound = userViewModel.isFindingFriend

            // if the friend is not found than pop back a screen
            if (!friendFound) {
                navController.popBackStack()
            }
            // if the friend is being found, display a progress bar
            if (friend == null) {
                NavigationContainer(navController = navController) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(90.dp)
                    )
                }
            } else {
                // If the friend is found than display the friend
                FriendDetailScreen(
                    userViewModel = userViewModel,
                    friend = friend,
                    navController = navController
                )
            }

        }


        // Navigation for the profile screen
        composable(
            route = Route.ProfileScreen.route,
        ) {
            // Profile screen with bottom navigation
            // User should always be authenticated when they are in the app
            if (authViewModel.user == null) {
                navController.popBackStack()
            }
            // Get the user from the database
            userViewModel.getUser(
                authViewModel.user!!.email!!.replace("@gmail.com", ""),
                context = context
            )
            // if the user is not found show a progress bar
            if (userViewModel.user == null) {
                NavigationContainer(navController = navController) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(90.dp)
                    )
                }
            } else {
                // Profile screen with bottom navigation
                NavigationContainer(navController = navController) {
                    ProfileScreen(
                        userViewModel = userViewModel
                    )
                }
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
                userViewModel = userViewModel
            )
        }

        // In game composable
        // It will handle the different games content
        composable(
            route = Route.GameScreen.route
        ) {
            /**
             * Route of the current screen
             */
            val currentRoute = navController.currentBackStackEntryAsState()
                .value?.destination?.route

            // If the game has not been created, pop back one screen
            // This is needed for the screen when the user leaves the
            // application or
            // if any error occurs while playing the game, I.E. Network error leave the game
            if (gameViewModel.errorMessage != null || !gameViewModel.createGameRoomCalled) {
                // if the current route it the game than pop back a screen
                if (currentRoute == Route.GameScreen.route) {
                    navController.popBackStack()
                }
            }
            // This statement will handle removing the user from the database
            // Whenever the user clicks on the back button while they are either
            // waiting in the queue, or in a game
            if (currentRoute != Route.GameScreen.route && currentRoute != null && gameViewModel.game != null) {
                // Check if the game has been created or not
                // Remove the game
                gameViewModel.removeUserFromGame()
            }
            // If the user has clicked on report user
            // open the report user screen
            if (gameViewModel.isReportScreenOpened) {
                // Report screen display
                ReportScreen(
                    navController = navController,
                    gameViewModel = gameViewModel
                )
                // If the user is adding the user as a friend and the game has currently
                // all the members inside than display the add as queue screen
            } else if (gameViewModel.isAddAsFriendScreenOpened &&
                gameViewModel.game?.members?.size == gameViewModel.game?.maxMembers
            ) {
                // If both the friends have added each other show the screen for users added
                if (gameViewModel.game?.addFriendList?.size == gameViewModel.game?.maxMembers) {
                    FriendAddedScreen(navController = navController)
                } else {
                    // Display the queue for adding a user as a friend
                    AddFriendQueueScreen(gameViewModel = gameViewModel)
                }
                // If the game has started and not ended and all the members are in the game
                // than display the game screen a
            } else if (gameViewModel.game?.isGameStarted == true &&
                gameViewModel.game?.members?.size == gameViewModel.game?.maxMembers &&
                gameViewModel.game?.isGameEnded == false
            ) {
                // Use the game navigator to direct to the proper game
                GameNavigator(gameViewModel = gameViewModel)
                // If the game has ended or a user leaves in the middle of the game display the
                // end game screen
            } else if (gameViewModel.game?.isGameEnded == true &&
                gameViewModel.game?.members?.filter { member -> member.username != gameViewModel.savedUsername }?.size == 1
            ) {
                EndGameScreen(gameViewModel = gameViewModel)
                // If none of the above are true than display the game queue screen
            } else {
                // There is a bug in which when the user goes back from the game screen
                // The game queue screen will show up for a split second
                // so we check if the current screen route matches the game screen route
                // Reason is that there is a time when the current route is null
                if (currentRoute == Route.GameScreen.route) {
                    GameQueueScreen(navController = navController)
                }
            }
        }
    }
}