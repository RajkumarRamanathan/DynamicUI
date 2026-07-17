package com.anju.dynamic.core.renderer

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import com.anju.dynamic.core.model.Widget

class DefaultRendererFactory(
    private val renderers: Map<String, WidgetRenderer>,
    private val visibilityEvaluator: VisibilityEvaluator,
    private val context: Map<String, String> = emptyMap()
) : RendererFactory {

    @Composable
    override fun RenderWidget(widget: Widget) {
        val isVisible = visibilityEvaluator.isVisible(widget.visibility, context)
        
        val (enter, exit) = when (widget.animation) {
            "slide" -> slideInVertically() + fadeIn() to slideOutVertically() + fadeOut()
            "expand" -> expandVertically() + fadeIn() to shrinkVertically() + fadeOut()
            else -> fadeIn() to fadeOut()
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = enter,
            exit = exit
        ) {
            val renderer = renderers[widget.type]
            if (renderer != null) {
                renderer.Render(widget, this@DefaultRendererFactory)
            } else {
                // Fallback or Placeholder for unknown widget types
            }
        }
    }

    override fun getContext(): Map<String, String> = context
}
