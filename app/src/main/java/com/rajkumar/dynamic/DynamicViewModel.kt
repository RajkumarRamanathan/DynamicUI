package com.rajkumar.dynamic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajkumar.dynamic.core.model.Screen
import com.rajkumar.dynamic.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.rajkumar.dynamic.data.DynamicRepository

@HiltViewModel
class DynamicViewModel @Inject constructor(
    val navigationManager: NavigationManager,
    private val repository: DynamicRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<Screen?>(null)
    val uiState: StateFlow<Screen?> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadScreen(screenId: String, isAiPrompt: Boolean = false) {
        viewModelScope.launch {
            _isLoading.value = true
            val screen = if (isAiPrompt) {
                repository.getAiScreen(screenId)
            } else {
                repository.getScreen(screenId)
            }
            _uiState.value = screen
            _isLoading.value = false
        }
    }

    fun navigate(route: String) {
        viewModelScope.launch {
            navigationManager.navigate(route)
        }
    }

    /**
     * Simulates processing a chat request using an on-device AI intent classifier.
     * In a real scenario, this would use Google AICore or MediaPipe.
     */
    fun handleChatRequest(query: String) {
        viewModelScope.launch {
            val intent = classifyIntent(query)
            val screenId = when (intent) {
                "show_balance" -> "balance"
                "show_analytics" -> "analytics"
                "show_transactions" -> "statement"
                "show_pay_bills" -> "pay_bills"
                "show_send_money" -> "send_money"
                "show_scan_qr" -> "scan_qr"
                "go_home" -> "home"
                "go_admin" -> "admin"
                "unknown" -> query // Keep the raw query for AI
                else -> null
            }

            if (intent == "go_admin") {
                navigate("admin")
            } else if (intent == "unknown" && screenId != null) {
                loadScreen(screenId, isAiPrompt = true)
            } else if (screenId != null) {
                loadScreen(screenId)
            } else {
                _isLoading.value = false
            }
        }
    }

    private fun classifyIntent(query: String): String {
        val lower = query.lowercase()
        return when {
            lower.contains("admin") -> "go_admin"
            else -> "unknown"
        }
    }

    suspend fun updateBalance(amount: Double): Boolean {
        return repository.updateBalance(amount)
    }

    suspend fun createPage(pageId: String, title: String, content: String): Boolean {
        return repository.createPage(pageId, title, content)
    }
}
