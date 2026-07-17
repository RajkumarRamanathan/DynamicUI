package com.anju.dynamic.core.renderer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anju.dynamic.core.model.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRenderer(
    screen: Screen,
    rendererFactory: RendererFactory
) {
    Scaffold(
        topBar = {
            screen.title?.let {
                TopAppBar(title = { Text(text = it) })
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            rendererFactory.RenderWidget(screen.content)
        }
    }
}
