package ca.finalfive.friendsup.models

import com.google.type.DateTime
import java.time.Instant
import java.time.Instant.now
import java.time.LocalDateTime
import java.util.*

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
