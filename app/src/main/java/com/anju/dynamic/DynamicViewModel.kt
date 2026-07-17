package com.anju.dynamic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anju.dynamic.core.model.Screen
import com.anju.dynamic.mock.api.MockData
import com.anju.dynamic.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DynamicViewModel @Inject constructor(
    val navigationManager: NavigationManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<Screen?>(null)
    val uiState: StateFlow<Screen?> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadScreen(screenId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000) // Realistic delay
            val screen = when (screenId) {
                "home" -> MockData.getHomeScreen()
                "balance" -> MockData.getBalanceScreen()
                "statement" -> MockData.getStatementScreen()
                "analytics" -> MockData.getAnalyticsScreen()
                "pay_bills" -> MockData.getPayBillsScreen()
                "send_money" -> MockData.getSendMoneyScreen()
                "scan_qr" -> MockData.getScanQrScreen()
                else -> MockData.getHomeScreen()
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
                else -> null
            }

            if (screenId != null) {
                loadScreen(screenId)
            } else {
                // Thinking delay for unknown intents to make it realistic
                _isLoading.value = true
                delay(2000)
                _isLoading.value = false
            }
        }
    }

    private fun classifyIntent(query: String): String {
        val lower = query.lowercase()
        return when {
            lower.contains("balance") || lower.contains("money") || lower.contains("account") -> "show_balance"
            lower.contains("spend") || lower.contains("chart") || lower.contains("analytics") || lower.contains("graph") -> "show_analytics"
            lower.contains("history") || lower.contains("statement") || lower.contains("transactions") -> "show_transactions"
            lower.contains("pay") || lower.contains("bill") -> "show_pay_bills"
            lower.contains("send") || lower.contains("transfer") -> "show_send_money"
            lower.contains("scan") || lower.contains("qr") -> "show_scan_qr"
            lower.contains("home") || lower.contains("main") -> "go_home"
            else -> "unknown"
        }
    }
}
