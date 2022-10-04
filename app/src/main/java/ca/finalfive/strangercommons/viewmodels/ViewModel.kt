package ca.finalfive.strangercommons.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Temporary View Model
class MyViewModel: ViewModel() {

    var name by mutableStateOf("")

    fun getName(myName: String) {
        viewModelScope.launch {
            // Get instance of the movie
            name = myName
        }
    }

}