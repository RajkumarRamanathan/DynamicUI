package com.rajkumar.dynamic.core.renderer

import javax.inject.Inject
import javax.inject.Singleton

interface VisibilityEvaluator {
    fun isVisible(expression: String?, context: Map<String, String>): Boolean
}

@Singleton
class DefaultVisibilityEvaluator @Inject constructor() : VisibilityEvaluator {
    override fun isVisible(expression: String?, context: Map<String, String>): Boolean {
        if (expression.isNullOrBlank()) return true
        
        return try {
            // Support multiple conditions with &&
            val conditions = expression.split("&&").map { it.trim() }
            conditions.all { condition ->
                evaluateSingleCondition(condition, context)
            }
        } catch (e: Exception) {
            true // Default to visible on error
        }
    }

    private fun evaluateSingleCondition(condition: String, context: Map<String, String>): Boolean {
        return when {
            condition.contains("==") -> {
                val (key, value) = condition.split("==").map { it.trim() }
                context[key] == value
            }
            condition.contains("!=") -> {
                val (key, value) = condition.split("!=").map { it.trim() }
                context[key] != value
            }
            condition.contains(">=") -> {
                val (key, value) = condition.split(">=").map { it.trim() }
                (context[key]?.toFloatOrNull() ?: 0f) >= (value.toFloatOrNull() ?: 0f)
            }
            condition.contains("<=") -> {
                val (key, value) = condition.split("<=").map { it.trim() }
                (context[key]?.toFloatOrNull() ?: 0f) <= (value.toFloatOrNull() ?: 0f)
            }
            condition.contains(">") -> {
                val (key, value) = condition.split(">").map { it.trim() }
                (context[key]?.toFloatOrNull() ?: 0f) > (value.toFloatOrNull() ?: 0f)
            }
            condition.contains("<") -> {
                val (key, value) = condition.split("<").map { it.trim() }
                (context[key]?.toFloatOrNull() ?: 0f) < (value.toFloatOrNull() ?: 0f)
            }
            else -> context[condition]?.toBoolean() ?: false
        }
    }
}
