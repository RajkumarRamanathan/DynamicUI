package com.anju.dynamic.core.renderer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anju.dynamic.core.model.Widget
import com.anju.dynamic.core.ui.components.ShimmerItem
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.jsonPrimitive

class BalanceCardRenderer(private val onAction: (com.anju.dynamic.core.model.Action) -> Unit) : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val balance = widget.properties?.get("balance")?.jsonPrimitive?.content ?: "₹0.00"
        val accountType = widget.properties?.get("account_type")?.jsonPrimitive?.content ?: ""
        val accountNumber = widget.properties?.get("account_number")?.jsonPrimitive?.content ?: ""

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { widget.actions?.get("onClick")?.let { onAction(it) } },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = accountType, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = balance, color = Color.White, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = accountNumber, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

class TransactionItemRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val title = widget.properties?.get("title")?.jsonPrimitive?.content ?: ""
        val subtitle = widget.properties?.get("subtitle")?.jsonPrimitive?.content ?: ""
        val amount = widget.properties?.get("amount")?.jsonPrimitive?.content ?: ""
        val date = widget.properties?.get("date")?.jsonPrimitive?.content ?: ""

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Icon placeholder
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(horizontalAlignment = Alignment.End) {
                val isPositive = amount.startsWith("+")
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (isPositive) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onSurface
                )
                Text(text = date, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

class ProgressItemRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val label = widget.properties?.get("label")?.jsonPrimitive?.content ?: ""
        val progress = widget.properties?.get("progress")?.jsonPrimitive?.floatOrNull ?: 0f
        val details = widget.properties?.get("details")?.jsonPrimitive?.content ?: ""

        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = label, style = MaterialTheme.typography.bodyMedium)
                Text(text = details, style = MaterialTheme.typography.labelSmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
        }
    }
}

class QuickActionRenderer(private val onAction: (com.anju.dynamic.core.model.Action) -> Unit) : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val label = widget.properties?.get("label")?.jsonPrimitive?.content ?: ""
        
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable { widget.actions?.get("onClick")?.let { onAction(it) } },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Icon would go here
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium)
        }
    }
}

class ShimmerRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val height = widget.properties?.get("height")?.jsonPrimitive?.floatOrNull?.dp ?: 100.dp
        ShimmerItem(height = height, modifier = Modifier.padding(16.dp))
    }
}

class LazyColumnRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            widget.children?.let { children ->
                items(children) { child ->
                    rendererFactory.RenderWidget(child)
                }
            }
        }
    }
}

class BillCardRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val provider = widget.properties?.get("provider")?.jsonPrimitive?.content ?: ""
        val amount = widget.properties?.get("amount")?.jsonPrimitive?.content ?: ""
        val dueDate = widget.properties?.get("due_date")?.jsonPrimitive?.content ?: ""
        val type = widget.properties?.get("type")?.jsonPrimitive?.content ?: ""
        val details = widget.properties?.get("card_number")?.jsonPrimitive?.content
            ?: widget.properties?.get("phone_number")?.jsonPrimitive?.content ?: ""

        val cardBrush = when (type) {
            "credit_card" -> androidx.compose.ui.graphics.Brush.linearGradient(
                colors = listOf(Color(0xFF283593), Color(0xFF1A237E), Color(0xFF0D47A1))
            )
            "phone_bill" -> androidx.compose.ui.graphics.Brush.linearGradient(
                colors = listOf(Color(0xFF00695C), Color(0xFF004D40), Color(0xFF00796B))
            )
            else -> androidx.compose.ui.graphics.Brush.linearGradient(
                colors = listOf(Color(0xFF455A64), Color(0xFF37474F), Color(0xFF263238))
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(cardBrush)
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(
                                text = provider,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = details,
                                color = Color.White.copy(alpha = 0.7f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if (type == "credit_card") {
                            Surface(
                                modifier = Modifier.size(40.dp, 25.dp),
                                color = Color(0xFFFFD700).copy(alpha = 0.8f),
                                shape = RoundedCornerShape(4.dp)
                            ) {} // Chip
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                text = "Amount Due",
                                color = Color.White.copy(alpha = 0.7f),
                                style = MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = amount,
                                color = Color.White,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = dueDate,
                                color = if (dueDate.contains("Overdue")) Color(0xFFFF5252) else Color.White.copy(alpha = 0.9f),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { /* Action */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    "Pay Now",
                                    color = Color(0xFF1A237E),
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class PayeeItemRenderer : WidgetRenderer {
    @Composable
    override fun Render(widget: Widget, rendererFactory: RendererFactory) {
        val name = widget.properties?.get("name")?.jsonPrimitive?.content ?: ""
        val details = widget.properties?.get("details")?.jsonPrimitive?.content ?: ""
        val country = widget.properties?.get("country")?.jsonPrimitive?.content ?: ""

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        if (country == "Indian") Color(0xFFFF9800).copy(alpha = 0.2f) else Color(0xFF2196F3).copy(alpha = 0.2f),
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.split(" ").mapNotNull { it.firstOrNull() }.joinToString("").take(2),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (country == "Indian") Color(0xFFE65100) else Color(0xFF0D47A1),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                Text(text = details, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(
                text = if (country == "Indian") "🇮🇳" else "🇺🇸",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
