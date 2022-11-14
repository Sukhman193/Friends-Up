package ca.finalfive.friendsup.repositories

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * The Firebase Auth Repository
 */
class FirebaseAuthRepository {
    // Google Sign-in Client variable
    private lateinit var googleSignInClient: GoogleSignInClient
    // Firebase Authentication Instance
    var auth = FirebaseAuth.getInstance()

    /**
     * Sign-In with Google function
     * @param token - Google Sign-In Token
     * @param context - The Context
     * @param launcher - The Activity Launcher
     * @return Firebase User
     */
    fun signIn(token: String, context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult> ): FirebaseUser? {
        // Building the Google Sign-in Options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
        // Getting the client and storing it in the google sign-in client
        googleSignInClient = GoogleSignIn.getClient(context, gso)
        // getting a new instance
        auth = FirebaseAuth.getInstance()
        // call the launcher to launch the client
        launcher.launch(googleSignInClient.signInIntent)
        // return the user
        return Firebase.auth.currentUser
    }

}