package com.rajkumar.dynamic.core.renderer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rajkumar.dynamic.core.model.Widget
import kotlinx.serialization.json.jsonPrimitive

class TextRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val text = widget.properties?.get("text")?.jsonPrimitive?.content ?: ""
        Text(text = text)
    }
}

class ColumnRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        Column {
            widget.children?.forEach { child ->
                rendererFactory.RenderWidget(child)
            }
        }
    }
}

class RowRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        Row {
            widget.children?.forEach { child ->
                rendererFactory.RenderWidget(child)
            }
        }
    }
}

class ButtonRenderer(private val onAction: (com.rajkumar.dynamic.core.model.Action) -> Unit) : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val text = widget.properties?.get("text")?.jsonPrimitive?.content ?: ""
        Button(onClick = {
            widget.actions?.get("onClick")?.let { onAction(it) }
        }) {
            Text(text = text)
        }
    }
}
