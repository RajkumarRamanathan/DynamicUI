package com.rajkumar.dynamic.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {
    private val _commands = MutableSharedFlow<String>()
    val commands = _commands.asSharedFlow()

    suspend fun navigate(route: String) {
        _commands.emit(route)
    }
}
