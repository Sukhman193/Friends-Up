package ca.finalfive.friendsup.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.finalfive.friendsup.viewmodels.GameViewModel

/**
 * Factory for game view model
 */
class GameViewModelFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // returns the game view model instance
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel() as T
        }
        // This should never happen
        throw IllegalStateException()
    }
}