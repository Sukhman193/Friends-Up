package ca.finalfive.friendsup.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.friendsup.models.User
import ca.finalfive.friendsup.repositories.FirestoreUserRepository
import ca.finalfive.friendsup.services.ValidationService
import kotlinx.coroutines.launch
import ca.finalfive.friendsup.helpers.Error
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

/**
 * UserViewModel - stores the and handles the user's functionality
 * @param userRepository - An instance of Firestore User Repository
 */
class UserViewModel(private val userRepository: FirestoreUserRepository): ViewModel() {
    // saved the user's object
    var user: User? by mutableStateOf(userRepository.firestoreUser)

    val validationService = ValidationService()

    // friend user which by default is null
    var friend: User? by mutableStateOf(userRepository.friend)
    // a boolean statement to see if the friend is found
    var isFriendFound: Boolean? by mutableStateOf(userRepository.isFriendFound)

    /**
     * addUser - calls the add function in firestore user repository to add the user to database
     * @param newUser - User object
     */
    fun addUser(newUser: User){
        viewModelScope.launch {
            userRepository.addUser(newUser)
        }
    }

    /**
     * getUser - calls the get function in firestore user repository to retrieve the user
     * @param userId - id of the user
     */
    fun getUser(userId: String){
        viewModelScope.launch {
            userRepository.getUserById(userId)
            user = userRepository.firestoreUser
        }
    }

    /**
     * updateUserByID - calls the update function in firestore user repository to update the user
     * @param userId - id of the user
     * @param updatedUser - an object of the updated user's information
     */

    fun updateUserByID(userId: String, updatedUser: User, context: Context){
        // Instance of Validation Service
        val validationService = ValidationService.getInstance()
        try {
            // validating the phone number
            if (updatedUser.phone != ""){
                validationService.isPhoneNumber(updatedUser.phone)
            }

            // validating the instagram
            if (updatedUser.instagram != ""){
                validationService.isInstagramValid(updatedUser.instagram)
            }

            // validating the snapchat
            if (updatedUser.snapchat != ""){
                validationService.isSnapchatValid(updatedUser.snapchat)
            }

            // validating the username
            validationService.isUsernameValid(updatedUser.username)

            // validating the discord
            if (updatedUser.discord != ""){
                validationService.isDiscordValid(updatedUser.discord)
            }
        }catch (e : Error.ValidationException){
            // make the toast and let the user know the message
            e.makeToast(context = context)
            return
        }
        // if everything matches it will update the database
        viewModelScope.launch {
            userRepository.updateUserByID(userId, updatedUser)
            user = userRepository.firestoreUser
        }
    }

    /**
     * findFriendById - calls the find friends function from user repository
     * @param userId - id of the user's friend
     * @param context - local context
     */
    suspend fun findFriendById(userId: String, context: Context) {
        try {
            // calling the findFriendById function to get the friend user
            userRepository.findFriendById(userId = userId)
            // updating the friend user
            friend = userRepository.friend
            isFriendFound = userRepository.isFriendFound
        } catch (e: Exception){
            // make a toast
            Error.ValidationException(e.toString()).makeToast(context = context)
        }
    }

    /**
     * deleteFriendById - calls the deleteFriend function from user repository
     * @param context - local context
     */
    suspend fun deleteFriend(context: Context) {
        try {
            // calling the findFriendById function to get the friend user
            userRepository.deleteFriend()
        } catch (e: Exception){
            // make a toast
            Error.ValidationException(e.toString()).makeToast(context = context)
        }
    }
}