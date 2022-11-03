package ca.finalfive.strangercommons.composables

import android.icu.number.Scale
import android.util.Log
import android.view.Gravity.FILL
import android.widget.GridLayout.FILL
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.MainActivity
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.navigation.Navigation
import ca.finalfive.strangercommons.navigation.Route
import ca.finalfive.strangercommons.viewmodels.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun AuthPage(authViewModel: AuthViewModel, navController: NavController){

    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
//                authViewModel.user = Firebase.auth.currentUser
                navController.navigate(Route.GameRoomScreen.route)
                Log.d("SUCCESS",authResult.toString())
            }
        } catch (e: ApiException) {
            Log.w("TAG", "Google sign in failed", e)
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            alignment = Alignment.CenterVertically
        ),

    ) {
        Text(text = "Stranger Commons", style = MaterialTheme.typography.h1)
        
        Text(text = "Are You Ready For The Toughest Challenges Of All?")

        Image(
            painter = painterResource(id = R.drawable.google_button),
            contentDescription = "SigninBut",
            modifier = Modifier
                .clickable(true, onClick = {
                    authViewModel.signIn(token, context, launcher)
                    //user = authViewModel.user
                    Log.d("USER->>>", authViewModel.user?.displayName.toString())

                })
                .size(122.dp)
        )
    }

}