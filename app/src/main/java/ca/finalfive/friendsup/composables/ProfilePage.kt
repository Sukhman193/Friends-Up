package ca.finalfive.friendsup.composables

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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import ca.finalfive.friendsup.composables.utils.CustomTextField
import ca.finalfive.friendsup.R
import ca.finalfive.friendsup.models.User
import ca.finalfive.friendsup.viewmodels.AuthViewModel
import ca.finalfive.friendsup.viewmodels.UserViewModel

/**
 * Profile Page
 */
@Composable
fun ProfilePage(userViewModel: UserViewModel){
    // saves the state of Focus Request for the keyboard
    val requester = remember {
        FocusRequester()
    }
    // States of keyboard
    var isKeyboardShown by remember { mutableStateOf(false)}

    // username of the user && the setter to change the username
    val (usernameText, setUsernameText) = rememberSaveable {
        if (userViewModel.user != null){
            mutableStateOf(userViewModel.user!!.username)
        } else {
            mutableStateOf("")
        }
    }
    // snapchat account of the user && the setter to change it
    val (snapchatText, setSnapchatText) = rememberSaveable {
        if (userViewModel.user != null) {
            mutableStateOf(userViewModel.user!!.snapchat)
        } else {
            mutableStateOf("")
        }
    }
    // instagram account of the user && the setter to change it
    val (instagramText, setInstagramText) = rememberSaveable {
        if (userViewModel.user != null) {
            mutableStateOf(userViewModel.user!!.instagram)
        } else {
            mutableStateOf("")
        }
    }
    // discord account of the user && the setter to change it
    val (discordText, setDiscordText) = rememberSaveable {
        if (userViewModel.user != null) {
            mutableStateOf(userViewModel.user!!.discord)
        } else {
            mutableStateOf("")
        }
    }
    // phone number of the user && the setter to change it
    val (phoneText, setPhoneText) = rememberSaveable {
        if (userViewModel.user != null) {
            mutableStateOf(userViewModel.user!!.phone)
        } else {
            mutableStateOf("")
        }
    }

    /**
     * Checking with database to match the data
     */
    fun checkWithDatabase(): Boolean{
        if(
            userViewModel.user?.username != usernameText
            || userViewModel.user?.snapchat != snapchatText
            || userViewModel.user?.phone != phoneText
            || userViewModel.user?.discord != discordText
            || userViewModel.user?.instagram != instagramText
        ){
            return true
        }
        return false
    }

    /**
     * saves the new updated data into database
     */
    fun saveHandler(){
        // user id
        val userId = userViewModel.user?.email?.replace("@gmail.com","")
        // saves the new data as a User object
        val updatedUser = userViewModel.user?.let {
            User(
                username = usernameText,
                snapchat = snapchatText,
                discord = discordText,
                instagram = instagramText,
                phone = phoneText,
                email = it.email
            )
        }
        // sends the updated data to the user view model
        if (userId != null) {
            if (updatedUser != null) {
                userViewModel.updateUserByID(userId, updatedUser)
                userViewModel.getUser(userId)
            }
        }

    }

    /**
     * resets all the changed data back to normal
     */
    fun cancelHandler(){
        if(userViewModel.user != null){
            // reset the username
            setUsernameText(userViewModel.user!!.username)
            // reset the instagram
            setInstagramText(userViewModel.user!!.instagram)
            // reset the discord
            setDiscordText(userViewModel.user!!.discord)
            // reset the snapchat
            setSnapchatText(userViewModel.user!!.snapchat)
            // reset the phone number
            setPhoneText(userViewModel.user!!.phone)
        }

    }

    // saves the state of the local focus
    val localFocusManager = LocalFocusManager.current

    // structure for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // checks if the user taps outside the textField
                detectTapGestures {
                    localFocusManager.clearFocus()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // The title depending on the keyboard
        ScreenTitle(title =
            if(isKeyboardShown){
                stringResource(id = R.string.edit_title)
            }
            // if the keyboard is disabled then show the title as Profile
            else {
                stringResource(id = R.string.profile_title)
            },
        )
        // if the keyboard is disabled then show the description of the profile page
        if (!isKeyboardShown){
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
            Spacer(modifier = Modifier.size(25.dp))
        }
        // text field that shows the user's information
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // the customized TextField to show the user's username
            CustomTextField(
                value = usernameText,
                setValue = setUsernameText,
                label = stringResource(id = R.string.profile_username),
                iconID = R.drawable.username_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
                    .padding()
            )

            // the customized TextField to show the user's snapchat account
            CustomTextField(
                value = snapchatText,
                setValue = setSnapchatText ,
                label = stringResource(id = R.string.profile_snapchat),
                iconID = R.drawable.snapchat_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

            // the customized TextField to show the user's instagram account
            CustomTextField(
                value = instagramText,
                setValue = setInstagramText ,
                label = stringResource(id = R.string.profile_instagram) ,
                iconID = R.drawable.instagram_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

            // the customized TextField to show the user's discord account
            CustomTextField(
                value = discordText,
                setValue = setDiscordText ,
                label = stringResource(id = R.string.profile_discord),
                iconID = R.drawable.discord_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

            // the customized TextField to show the user's phone number
            CustomTextField(
                value = phoneText,
                setValue = setPhoneText ,
                label = stringResource(id = R.string.profile_phone_number) ,
                iconID = R.drawable.phone_icon,
                modifier = Modifier
                    .focusRequester(requester)
                    .onFocusChanged { isKeyboardShown = it.hasFocus }
            )

        }
        // if the user changes any of the fields which are not equal to the database
        // it will popup the save or cancel buttons
        if (
            checkWithDatabase()
        ){
            // interaction buttons for the user to change
            // its data when the user's information gets changed
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // cancel button to resets the data
                Button(
                    onClick = { cancelHandler() },
                    colors = ButtonDefaults.buttonColors(backgroundColor =
                    colorResource(
                        id = R.color.cancelRed
                    ),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(83.dp)
                ) {
                    Text(text = "Cancel")
                }
                // save button to update the new data
                Button(
                    onClick = { saveHandler() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(
                            id = R.color.saveGreen
                        ),
                    ),
                    modifier = Modifier.width(83.dp)
                ) {
                    Text(text = "Save", color = Color.White)
                }
            }
        }
    }
}