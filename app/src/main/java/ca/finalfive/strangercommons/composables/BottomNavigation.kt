package ca.finalfive.strangercommons.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.navigation.BottomNavItem
import ca.finalfive.strangercommons.ui.theme.BottomNavBackgroundColor
import ca.finalfive.strangercommons.ui.theme.BottomNavSelectedColor


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
    val navigationItems: List<BottomNavItem> = listOf(
        BottomNavItem.Friends,
        BottomNavItem.Home,
        BottomNavItem.Profile
    )

    //  https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
    // Bottom navigation
    BottomNavigation(
        modifier = Modifier.height(67.dp)
    ) {
        // Iterate over each value of the navigation item
        navigationItems.forEach { item ->
            // Check if the current navigation item is selected or not
            val selected = currentRoute?.startsWith(item.route) ?: false
            // Background color for the navigation selection
            val backgroundColor = if(selected) {
                BottomNavSelectedColor
            } else {
                BottomNavBackgroundColor
            }
            // Bottom navigation items
            BottomNavigationItem(
                modifier = Modifier
                    .background(backgroundColor),
                // Is the item currently selected
                selected = selected,
                icon = {
                    // Icon of the navigation
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(35.dp)
                    )
                },
                // Label text
                label = { Text(text = item.title) },
                // foreground color when selected
                selectedContentColor = Color.White,
                // always show the label
                alwaysShowLabel = true,
                // foreground color when not selected
                unselectedContentColor = Color.White,
                // when clicked, navigate to the page selected
                onClick = {
                    navController.navigate(item.route)
                }
            )

        }
    }
}