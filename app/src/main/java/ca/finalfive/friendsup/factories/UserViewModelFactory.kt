package ca.finalfive.friendsup.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.finalfive.friendsup.repositories.FirestoreUserRepository
import ca.finalfive.friendsup.viewmodels.UserViewModel

/**
 * UserViewModelFactory - factorize the userViewModel
 * @param userRepository - an instance of the FirestoreUserRepository
 */
class UserViewModelFactory(private val userRepository: FirestoreUserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // returns the userViewModel instance
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }

        throw IllegalStateException()
    }
}