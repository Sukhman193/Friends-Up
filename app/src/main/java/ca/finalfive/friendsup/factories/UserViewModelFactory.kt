package ca.finalfive.strangercommons.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.finalfive.strangercommons.repositories.FirestoreUserRepository
import ca.finalfive.strangercommons.viewmodels.UserViewModel

class UserViewModelFactory(private val userRepository: FirestoreUserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T;
        }
        throw IllegalStateException();
    }
}