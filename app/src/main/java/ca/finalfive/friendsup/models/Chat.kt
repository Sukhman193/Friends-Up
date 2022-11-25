package ca.finalfive.friendsup.models

/**
 * Chat of the users sent during the gameplay
 * @param sentBy username of the user who sent the message
 * @param icon Icon of the user who sent the message
 * @param content Content of the message
 */
data class Chat(
    /**
     * Id of the chat
     */
    val id: String = "",
    /**
     * Username of the user who sent the message
     */
    val sentBy: String = "",
    /**
     * Profile picture of the user who sent the message
     */
    val icon: String = "",
    /**
     * Content of the message
     */
    val content: String = "",
)
