package com.anju.dynamic.core.renderer

import com.anju.dynamic.core.model.Action

interface ActionHandler {
    fun handleAction(action: Action)
}
