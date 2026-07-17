package com.rajkumar.dynamic.core.renderer

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.rajkumar.dynamic.core.model.Widget
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.jsonPrimitive

class LottieRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val assetName = widget.properties?.get("asset")?.jsonPrimitive?.content ?: return
        val iterations = if (widget.properties?.get("loop")?.jsonPrimitive?.booleanOrNull == true) {
            LottieConstants.IterateForever
        } else {
            1
        }
        val size = widget.properties?.get("size")?.jsonPrimitive?.floatOrNull?.dp ?: 200.dp

        val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = iterations
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(size)
        )
    }
}
