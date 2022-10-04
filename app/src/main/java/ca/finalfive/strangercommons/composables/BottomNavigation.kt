package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.models.NavigationTab
import ca.finalfive.strangercommons.navigation.Route
import ca.finalfive.strangercommons.ui.theme.Purple700

@Composable
fun BottomNavigation(navController: NavController) {

    // https://stackoverflow.com/questions/66493551/jetpack-compose-navigation-get-route-of-current-destination-as-string
    /**
     * Route of the current page that is being displayed
     */
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    /**
     * List of the different options for the bottom navigation
     */
    val navigationTab: List<NavigationTab> = listOf(
        NavigationTab(name = "Friends", icon = R.drawable.friend_icon, route = Route.FriendsScreen.route),
        NavigationTab(name = "Chat room", icon = R.drawable.message_icon, route = Route.ChatRoomScreen.route),
        NavigationTab(name = "Profile", icon = R.drawable.profile_icon, route = Route.ProfileScreen.route)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Map over the array of the navigation tabs
        navigationTab.map { nav ->
            Column(
                modifier = Modifier
                    .clickable {
                        navController.navigate(nav.route)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = nav.icon),
                    contentDescription = nav.name,
                    tint = if(currentRoute!!.startsWith(nav.route)){
                        Purple700
                    } else {
                        MaterialTheme.colors.onBackground
                    },
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = nav.name)
            }
        }
    }
}