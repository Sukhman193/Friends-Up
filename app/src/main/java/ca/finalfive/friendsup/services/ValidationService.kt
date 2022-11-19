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
    fun isPhoneNumber(data: String) {
        val result = Constants.Regex.PHONE_NUMBER.matches(data)
        if(!result){
            throw Error.ValidationException("Phone number does not match")
        }
    }
    // TODO GET INSTANCE
//    companion object{
//
//    }

}