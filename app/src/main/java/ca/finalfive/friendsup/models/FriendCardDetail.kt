package ca.finalfive.friendsup.models

/**
 * Data class which includes the details of all the friend's
 * social media
 * @param socialMedia is the name of the current social media
 * @param value value of the social media content, like username value
 * @param icon
 */
data class FriendCardDetail(
    /**
     * Key of the hash map
     */
    val socialMedia: String,
    /**
     * Value of the hash map
     */
    val value: String,
    /**
     * Icon which represents the social media
     */
    val icon: Int
)
