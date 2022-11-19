package ca.finalfive.friendsup.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.friendsup.models.User
import ca.finalfive.friendsup.repositories.FirestoreUserRepository
import ca.finalfive.friendsup.services.ValidationService
import kotlinx.coroutines.launch

/**
 * UserViewModel - stores the and handles the user's functionality
 * @param userRepository - An instance of Firestore User Repository
 */
class UserViewModel(private val userRepository: FirestoreUserRepository): ViewModel() {
    // saved the user's object
    var user: User? by mutableStateOf(userRepository.firestoreUser)

    val validationService = ValidationService()

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
    fun updateUserByID(userId: String, updatedUser: User){

        viewModelScope.launch {
            userRepository.updateUserByID(userId, updatedUser)
            user = userRepository.firestoreUser
        }
    }
}