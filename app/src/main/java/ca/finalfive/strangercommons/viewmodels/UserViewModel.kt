package ca.finalfive.strangercommons.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.strangercommons.models.User
import ca.finalfive.strangercommons.repositories.FirestoreUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: FirestoreUserRepository): ViewModel() {
    val userStateFlow = MutableStateFlow<Result<*>?>(null)

    var user: User? by mutableStateOf(userRepository.firestoreUser)

//    init{
//        viewModelScope.launch {
//        }
//    }

    fun addUser(newUser: User){
        userRepository.addUser(newUser)
        user = newUser
    }

//    fun userFound(id: String): Boolean{
//        return userRepository.isUserExists(id)
//    }

    fun getuser(id: String){
        userRepository.getUserById(id)
        user = userRepository.firestoreUser
    }
}