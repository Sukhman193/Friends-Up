package ca.finalfive.strangercommons.viewmodels

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.finalfive.strangercommons.repositories.FirebaseAuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel() {

    var firebaseAuthRepository = FirebaseAuthRepository()
    var user : FirebaseUser? = firebaseAuthRepository.user

    fun signIn(token: String, context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>){
        viewModelScope.launch {
            firebaseAuthRepository.signIn(token, context, launcher)
        }

    }

}