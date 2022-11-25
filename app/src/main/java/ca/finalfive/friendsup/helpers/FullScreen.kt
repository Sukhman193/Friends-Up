package ca.finalfive.friendsup.helpers

import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController

/**
 * Make the application full screen depending on which
 * version of android the user is using
 * @param window window created during the application launch
 */
fun makeApplicationFullScreen(window: Window) {
    // If the current sdk is lower than 30, than we return
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        // Setting the full screen mode for android versions
        // which are below API 30 will conflict with the
        // WindowCompat.setDecorFitsSystemWindows() as a
        // result full screen mode will not be available
        // for them
        return
    }
    // Hide the status bar for modern versions of android
    window.insetsController?.apply {
        // Hide status bar
        hide(WindowInsets.Type.statusBars())
        // Add status bar behaviour for drop down
        systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}