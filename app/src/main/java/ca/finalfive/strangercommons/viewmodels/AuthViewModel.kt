package ca.finalfive.strangercommons.viewmodels

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.strangercommons.repositories.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private var firebaseAuthRepository = FirebaseAuthRepository()
//    var user: FirebaseUser? by mutableStateOf(firebaseAuthRepository.user)
    var user: FirebaseUser? by mutableStateOf(Firebase.auth.currentUser)

    fun signIn(token: String, context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>){
//        viewModelScope.launch {
             user = firebaseAuthRepository.signIn(token, context, launcher)
//        }

    }

}