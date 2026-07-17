package com.rajkumar.dynamic.core.renderer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajkumar.dynamic.core.model.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class DynamicUIViewModel @Inject constructor(
    // In a real app, this would be a repository fetching from a remote API/LLM
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        object Loading : UiState()
        data class Success(val screen: Screen) : UiState()
        data class Error(val message: String) : UiState()
    }

    fun loadScreen(screenProvider: suspend () -> Screen) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val screen = screenProvider()
                _uiState.value = UiState.Success(screen)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
