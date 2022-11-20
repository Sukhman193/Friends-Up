package ca.finalfive.friendsup.services
import ca.finalfive.friendsup.helpers.Error


// https://github.com/sulealothman/sm-regex

/**
 * Constants Class - contains a Regex Class
 */
class Constants {
    /**
     * Regex Class - contains companion objects of different patterns such as phone number or username
     */
    class Regex {
        companion object {
            // Phone Number Regex Pattern
            val PHONE_NUMBER = Regex("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}\$")

            // Discord Username Regex Pattern
            val DISCORD_USERNAME = Regex("^.{3,32}#[0-9]{4}\$")

            // Instagram Username Regex Pattern
            // Matches with one point (sule.alothman) but not consecutive points (sule..alothman) and can added many points between char and char (sule.al.othman).
            // Starting period not matched (.sulealothman).
            // Ending period not matched (sulealothman.).
            // Match underscores (_sule_alothman_).
            // Max characters 30 char.
            val INSTAGRAM_USERNAME = Regex("^(?!.*\\\\.\\\\.|.*\\\\.\$)[A-z0-9][\\\\w.]+[A-z0-9]{0,30}\$")

            // Snapchat Username Regex Pattern
            // Matches with one symbol include in username (dot | undersocre | hyphen) (sule-alothman | sule.alothman| sule_alothman) but not consecutive symbols (sule--alothman | sule._alothman .. etc).
            // Usernames must start with a letter.
            // Usernames must end in a letter or number.
            // Max characters 15 char.
            val SNAPCHAT_USERNAME = Regex("^(?!.*\\\\.\\\\.|.*\\\\_\\\\_|.*\\\\-\\\\-)(?!.*\\\\.\$|.*\\\\_\$|.*\\\\-\$)(?!.*\\\\.\\\\-|.*\\\\-\\\\.|.*\\\\-\\\\_|.*\\\\_\\\\-|.*\\\\.\\\\_|.*\\\\_\\\\.)[a-zA-Z]+[\\\\w.-][0-9A-z]{0,15}\$")

            // User's Username Regex Pattern
            // Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
            // Username allowed of the dot (.), underscore (_), and hyphen (-).
            // The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
            // The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
            // The number of characters must be between 5 to 20.
            val USERNAME = Regex("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]\$")
        }
    }
}

/**
 * ValidationService class - contains functions to validate
 */
class ValidationService {
    /**
     * isPhoneNumber - validates the phone number
     * @param data - The phone number given to the function
     */
    fun isPhoneNumber(data: String) {
        if (!Constants.Regex.PHONE_NUMBER.matches(data)){
            throw Error.ValidationException("Phone Number Does Not Match")
        }
    }

    /**
     * isDiscordValid - validates the discord username
     * @param data - The discord username given to the function
     */
    fun isDiscordValid(data: String) {
        if (!Constants.Regex.DISCORD_USERNAME.matches(data)){
            throw Error.ValidationException("Discord Username Does Not Match")
        }
    }

    /**
     * isUsernameValid - validates the username
     * @param data - The username given to the function
     */
    fun isUsernameValid(data: String) {
        if (!Constants.Regex.USERNAME.matches(data)){
            throw Error.ValidationException("Username Does Not Match (It must be between 3-10)")
        }
    }

    /**
     * isInstagramValid - validates the Instagram Username
     * @param data - The instagram username given to the function
     */
    fun isInstagramValid(data: String) {
        if (!Constants.Regex.INSTAGRAM_USERNAME.matches(data)){
            throw Error.ValidationException("Instagram Username Does Not Match")
        }
    }

    /**
     * isSnapchatValid - validates the snapchat username
     * @param data - The snapchat username given to the function
     */
    fun isSnapchatValid(data: String){
        if (!Constants.Regex.SNAPCHAT_USERNAME.matches(data)){
            throw Error.ValidationException("Snapchat Username Does Not Match")
        }
    }

    /**
     * Validate the length of a text message
     * @param
     */
    fun isMessageValid(text: String, minLength: Int = 3, maxLength: Int = 30) {
        if(minLength > text.length || maxLength < text.length) {
            throw Error.ValidationException("Message must be between $minLength and $maxLength characters")
        }
    }


    companion object {
        /**
         * Singleton for the class
         */
        private var INSTANCE: ValidationService? = null

        /**
         * Get instance for the game firestore repository
         */
        fun getInstance(): ValidationService {
            // Check if an instance already exists
            if(INSTANCE == null) {
                INSTANCE = ValidationService()
            }
            // return the instance
            return INSTANCE!!
        }

    }

}