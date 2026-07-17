package com.rajkumar.dynamic.core.renderer

import androidx.compose.runtime.Composable
import com.rajkumar.dynamic.core.model.Widget

interface WidgetRenderer {
    @Composable
    fun Render(widget: Widget, rendererFactory: RendererFactory)
}

interface RendererFactory {
    @Composable
    fun RenderWidget(widget: Widget)
    fun getContext(): Map<String, String>
}
