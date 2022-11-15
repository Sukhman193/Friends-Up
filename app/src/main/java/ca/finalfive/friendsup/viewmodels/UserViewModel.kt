package ca.finalfive.friendsup.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.finalfive.friendsup.models.User
import ca.finalfive.friendsup.repositories.FirestoreUserRepository
import kotlinx.coroutines.flow.MutableStateFlow

class UserViewModel(private val userRepository: FirestoreUserRepository): ViewModel() {
    val userStateFlow = MutableStateFlow<Result<*>?>(null)

    var user: User? by mutableStateOf(userRepository.firestoreUser)


    fun addUser(newUser: User){
        userRepository.addUser(newUser)
        user = newUser
    }


    fun getuser(id: String){
        userRepository.getUserById(id)
        user = userRepository.firestoreUser
    }

    fun updateUserByID(userId: String, updatedUser: User){
        userRepository.updateUserByID(userId, updatedUser)
        user = userRepository.firestoreUser
    }
}