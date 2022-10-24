package ca.finalfive.strangercommons.repositories

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
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

class FirebaseAuthRepository {

    var user: FirebaseUser? = Firebase.auth.currentUser

    lateinit var googleSignInClient: GoogleSignInClient
    val Req_Code:Int=123
    var auth= FirebaseAuth.getInstance()

    //var googleSignInClient: GoogleSignInClient? = null


    suspend fun signIn(token: String, context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult> ){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)

        auth = FirebaseAuth.getInstance()

        launcher.launch(googleSignInClient.signInIntent)

        user = auth.currentUser
    }

}