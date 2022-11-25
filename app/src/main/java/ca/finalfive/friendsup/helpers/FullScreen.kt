package ca.finalfive.friendsup.helpers

import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager

/**
 * Make the application full screen depending on which
 * version of android the user is using
 * @param window window created during the application launch
 */
fun makeApplicationFullScreen(window: Window) {
    // If the current sdk is lower than 30, than use the
    // older flag to set the full screen mode
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        // Turn on full screen mode on older versions of android
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    } else {
        // Hide the status bar for modern versions of android
        window.insetsController?.apply {
            // Hide status bar
            hide(WindowInsets.Type.statusBars())
            // Add status bar behaviour for drop down
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}