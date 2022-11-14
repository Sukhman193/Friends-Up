package ca.finalfive.friendsup.models

data class User(
    val email: String,
    val username: String = "",
    val snapchat: String = "",
    val instagram: String = "",
    val discord: String = "",
    val phone: String = ""
)