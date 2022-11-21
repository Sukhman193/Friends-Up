package ca.finalfive.friendsup.models

/**
 * User model for the each users
 * @param email - contains the gmail of the user
 * @param username - contains the username of the user
 * @param snapchat - contains the snapchat of the user
 * @param instagram - contains the instagram of the user
 * @param phone - contains the phone number of the user
 * @param friendList - List of the user's friends
 */
data class User(
    // email of the user
    val email: String,
    // username of the user
    val username: String = "",
    // snapchat ID of the user
    val snapchat: String = "",
    // instagram account of the user
    val instagram: String = "",
    // discord ID of the user
    val discord: String = "",
    // phone number of the user
    val phone: String = "",
    // list of user's friends
    var friendList: List<String> = listOf()
)
{
    // Constructor required by the firestore deserialization
    constructor(): this("")
}