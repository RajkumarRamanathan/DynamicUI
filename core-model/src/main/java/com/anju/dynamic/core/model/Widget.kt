package com.anju.dynamic.core.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Widget(
    val type: String,
    val id: String? = null,
    val properties: JsonObject? = null,
    val children: List<Widget>? = null,
    val actions: Map<String, Action>? = null,
    val metadata: Map<String, String>? = null,
    val visibility: String? = null, // Logic expression
    val animation: String? = null // "fade", "slide", "bounce"
)

@Serializable
data class Action(
    val type: String,
    val payload: JsonObject? = null
)

@Serializable
data class Screen(
    val id: String,
    val title: String? = null,
    val metadata: Map<String, String>? = null,
    val content: Widget,
    val theme: Map<String, String>? = null
)
