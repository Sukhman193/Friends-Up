package ca.finalfive.friendsup.viewmodels

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.finalfive.friendsup.repositories.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * AuthViewModel class Extending the ViewModel()
 */
class AuthViewModel: ViewModel() {
    // making an instance of Firebase Repository
    private var firebaseAuthRepository = FirebaseAuthRepository()
    // Stating the user from the firebase instance
    var user: FirebaseUser? by mutableStateOf(Firebase.auth.currentUser)

    /**
     * Sign-In with Google function which calls the sign in function in the repository
     * and updates the user
     * @param token firebase client token
     * @param context main context of the application
     * @param launcher Activity launcher
     */
    fun signIn(token: String, context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>){
        user = firebaseAuthRepository.signIn(token, context, launcher)
    }
}