package ca.finalfive.strangercommons.models

/**
 * Navigation data class for the bottom bar
 * @param name Name of the navigation tab
 * @param icon ID corresponding to the drawable of the icon
 * @param route Route for the Navigation to take
 */
data class NavigationTab(
    val name: String,
    val icon: Int,
    val route: String
    )