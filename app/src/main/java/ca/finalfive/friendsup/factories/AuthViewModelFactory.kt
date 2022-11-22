package ca.finalfive.friendsup.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.finalfive.friendsup.repositories.FirebaseAuthRepository
import ca.finalfive.friendsup.viewmodels.AuthViewModel

/**
 * AuthViewModelFactory - factorize the authRepository
 * @param authRepository - an instance of the FirebaseAuthRepository
 */
class AuthViewModelFactory(private val authRepository: FirebaseAuthRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // returns the authviewmodel instance
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authRepository) as T;
        }

        throw IllegalStateException();
    }
}