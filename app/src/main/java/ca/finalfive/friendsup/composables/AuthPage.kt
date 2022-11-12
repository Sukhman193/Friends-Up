package ca.finalfive.friendsup.composables

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.navigation.Route
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * Composable Function To Show The Authentication Page
 * @param authViewModel: instance of AuthViewModel
 * @param navController: Nav Controller instance to navigate
 */
@Composable
fun AuthPage(authViewModel: AuthViewModel, navController: NavController){
    // local context
    val context = LocalContext.current
    // Firebase Client Token
    val token = stringResource(R.string.default_web_client_id)
    // Coroutine Scope
    val scope = rememberCoroutineScope()

    // The Google Sign-in Launcher
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        // returns a task for google sign in account
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            // returns the google sign in account
            val account = task.getResult(ApiException::class.java)!!
            // the Google Authentication credentials
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                // this async Firebase Function will use the credentials to sign in and returns the result
                Firebase.auth.signInWithCredential(credential).await()
                // Route to the Game Screen if the sign in is successful
                navController.navigate(Route.GameRoomScreen.route)

                Toast.makeText(context, "Authentication Successful", Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            // make a toast to notify the user that authentication was not successful
            Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
        }
    }
    // Container for the page
    Box {
        // Add Image background containing the moon
        BackgroundImage(showMoon = true)
        // structure for the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 45.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title of the page
            ScreenTitle(title = stringResource(id = R.string.app_name))
            // Description of the app
            Text(
                text = stringResource(id = R.string.authentication_description),
                style = MaterialTheme.typography.caption,
                fontSize = 27.sp,
                modifier = Modifier.padding(35.dp)
            )
            // Sign in with Google button
            Image(
                painter = painterResource(id = R.drawable.google_button),
                contentDescription = "Sign in Button",
                modifier = Modifier
                    .clickable(true, onClick = {
                        authViewModel.signIn(token, context, launcher)
                    })
                    .size(275.dp, 49.dp)
            )
        }
    }

}