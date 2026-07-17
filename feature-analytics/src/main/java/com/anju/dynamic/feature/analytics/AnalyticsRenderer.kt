package com.anju.dynamic.feature.analytics

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anju.dynamic.core.model.Widget
import com.anju.dynamic.core.renderer.RendererFactory
import com.anju.dynamic.core.renderer.WidgetRenderer
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.floatOrNull

class AnalyticsChartRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val title = widget.properties?.get("title")?.jsonPrimitive?.content ?: "Spending Analytics"
        val dataPoints = widget.properties?.get("data")?.jsonArray?.mapNotNull { 
            it.jsonPrimitive.floatOrNull 
        } ?: emptyList()

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            
            if (dataPoints.isNotEmpty()) {
                val modelProducer = remember(dataPoints) {
                    CartesianChartModelProducer.build {
                        lineSeries { series(dataPoints) }
                    }
                }
                
                CartesianChartHost(
                    chart = rememberCartesianChart(
                        rememberLineCartesianLayer(),
                        startAxis = rememberStartAxis(),
                        bottomAxis = rememberBottomAxis(),
                    ),
                    modelProducer = modelProducer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}
