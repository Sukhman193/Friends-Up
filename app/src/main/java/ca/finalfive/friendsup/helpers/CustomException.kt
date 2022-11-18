package ca.finalfive.friendsup.helpers

import android.content.Context
import android.widget.Toast

//TODO make a toast
/**
 * Error - a class that contains multiple Custom Exceptions
 */
class Error {
    /**
     * NotFoundException - a class that throws an exception when the user does not found
     * @param id - id of the object
     */
    class NotFoundException(message: String): Exception("User Not Found:  $message ")

    /**
     * NoNetworkException - a class that throws an exception when there is no internet connection
     */
    class NoNetworkException(): Exception("No Network Connection")

    class PhonePatternException(): Exception()

    /**
     * ValidationException
     * @param reason - The reason of the Error
     */
    class ValidationException(reason: String): Exception() {
        val title = reason

        fun makeToast(context: Context){
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
        }
    }

}
