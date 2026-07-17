package com.rajkumar.dynamic.core.renderer

import com.rajkumar.dynamic.core.model.Action

interface ActionHandler {
    fun handleAction(action: Action)
}
