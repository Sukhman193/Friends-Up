package ca.finalfive.strangercommons.repositories

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import ca.finalfive.strangercommons.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.CoroutineContext

/**
 * The Firebase Auth Repository
 */
class FirebaseAuthRepository {
    // Google Sign-in Client variable
    lateinit var googleSignInClient: GoogleSignInClient
    // Firebase Authentication Instance
    var auth= FirebaseAuth.getInstance()

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