package ca.finalfive.friendsup.services
import ca.finalfive.friendsup.helpers.Error
import kotlin.text.Regex

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
        }
    }
}

/**
 * ValidationService class - contains functions to validate
 */
class ValidationService{
    /**
     * isPhoneNumber - validates the phone number
     * @param data - The phone number given to the function
     */

    fun isPhoneNumber(data: String): Boolean {
        val result = Constants.Regex.PHONE_NUMBER.matches(data)
        if(!result){
            throw Error.ValidationException("Phone number does not match")
        }
        return true
    }

    /**
     * isDiscordValid - validates the phone number
     * @param data - The discord username given to the function
     */
    fun isDiscordValid(data: String): Boolean{
        return Constants.Regex.DISCORD_USERNAME.matches(data)
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
    // TODO GET INSTANCE
//    companion object{
//
//    }

}