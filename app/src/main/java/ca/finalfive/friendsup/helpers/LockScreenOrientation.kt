package ca.finalfive.friendsup.helpers

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// https://stackoverflow.com/questions/69230049/how-to-force-orientation-for-some-screens-in-jetpack-compose
@Composable
        /**
         * Lock the screen in one orientation
         * This can be reused for multiple screens
         * @param orientation is the orientation to use for the application
         * The above value can be accessed by ActivityInfo.SCREEN_ORIENTATION
         * Default to Portrait
         */
fun LockScreenOrientation(orientation: Int = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
    // Get the current context of the application
    val context = LocalContext.current
    // Get the current activity
    // If it's null than return
    val activity = context.findActivity() ?: return
    // set the current orientation
    activity.requestedOrientation = orientation
}

/**
 * Helper function for getting the context activity
 * @return Current application activity or null
 */
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
