package ca.finalfive.strangercommons.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.composables.utils.CustomOutlineTextField
import ca.finalfive.strangercommons.viewmodels.AuthViewModel
import ca.finalfive.strangercommons.viewmodels.UserViewModel

@Composable
fun ProfilePage(
    userViewModel: UserViewModel,
    authViewModel: AuthViewModel
){
//    userViewModel.getuser(authViewModel.user!!.email!!.replace("@gmail.com",""))
    Log.d("LLAMA", userViewModel.user.toString())
    val requester = remember {
        FocusRequester()
    }

    var isKeyboardShown by remember { mutableStateOf(false)}

    val (usernameText, setUsernameText) = rememberSaveable { mutableStateOf(userViewModel.user?.username) }

    val (snapchatText, setSnapchatText) = rememberSaveable { mutableStateOf(userViewModel.user?.snapchat) }

    val (instagramText, setInstagramText) = rememberSaveable { mutableStateOf(userViewModel.user?.instagram) }

    val (discordText, setDiscordText) = rememberSaveable { mutableStateOf(userViewModel.user?.discord) }

    val (phoneText, setPhoneText) = rememberSaveable { mutableStateOf(userViewModel.user?.phone) }

    val localFocusManager = LocalFocusManager.current

    // structure for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    localFocusManager.clearFocus()
                }
            }
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // The title
        Text(
            text =
                if(isKeyboardShown){
                    stringResource(id = R.string.edit_title)}
                else {
                    stringResource(id = R.string.profile_title)
                    },
            modifier = Modifier.padding(start = 120.dp),
            fontSize = 60.sp,
            style = MaterialTheme.typography.h1
        )
        
        if(!isKeyboardShown){
            Box(modifier = Modifier.padding(start = 20.dp)){
                Text(
                    text = stringResource(id = R.string.profile_description),
                    color = Color.White,
                    style = MaterialTheme.typography.body1,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(35.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(15.dp))
        }
        
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CustomOutlineTextField(
                value = usernameText!!,
                setValue = setUsernameText,
                label = stringResource(id = R.string.profile_username),
                iconID = R.drawable.username_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
                    .padding()
            )

            CustomOutlineTextField(
                value = snapchatText!!,
                setValue = setSnapchatText ,
                label = stringResource(id = R.string.profile_snapchat),
                iconID = R.drawable.snapchat_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

            CustomOutlineTextField(
                value = instagramText!!,
                setValue = setInstagramText ,
                label = stringResource(id = R.string.profile_instagram) ,
                iconID = R.drawable.instagram_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

            CustomOutlineTextField(
                value = discordText!!,
                setValue = setDiscordText ,
                label = stringResource(id = R.string.profile_discord),
                iconID = R.drawable.discord_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

            CustomOutlineTextField(
                value = phoneText!!,
                setValue = setPhoneText ,
                label = stringResource(id = R.string.profile_phone_number) ,
                iconID = R.drawable.phone_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

        }

        if (
            userViewModel.user?.username != usernameText
            || userViewModel.user?.snapchat != snapchatText
            || userViewModel.user?.phone != phoneText
            || userViewModel.user?.discord != discordText
            || userViewModel.user?.instagram != instagramText
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor =
                    colorResource(
                        id = R.color.cancelRed
                    ),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Cancel")
                }

                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Green,
                    )
                ) {
                    Text(text = "Save")
                }
            }
        }


    }
}