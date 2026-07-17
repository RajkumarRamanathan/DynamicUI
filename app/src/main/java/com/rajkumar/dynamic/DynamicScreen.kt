package com.rajkumar.dynamic

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rajkumar.dynamic.core.renderer.RendererFactory
import com.rajkumar.dynamic.core.renderer.ScreenRenderer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicScreen(
    screenId: String,
    viewModel: DynamicViewModel = hiltViewModel(),
    rendererFactory: RendererFactory // Now injected
) {
    val uiState by viewModel.uiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var showChat by remember { mutableStateOf(false) }
    var chatQuery by remember { mutableStateOf("") }
    var forceRefresh by remember { mutableStateOf(false) }
    var showInfo by remember { mutableStateOf(false) }

    LaunchedEffect(screenId) {
        if (screenId.isNotBlank()) {
            viewModel.loadScreen(screenId)
        }
    }

    Scaffold(
        floatingActionButton = {
            if (!showChat && !isLoading) {
                FloatingActionButton(
                    onClick = { showChat = true },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = "Banking Assistant")
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Watermark Background
            androidx.compose.foundation.Image(
                painter = androidx.compose.ui.res.painterResource(id = com.rajkumar.dynamic.R.drawable.ic_launcher_foreground),
                contentDescription = "App Watermark",
                modifier = Modifier
                    .fillMaxSize(0.6f)
                    .align(Alignment.Center)
                    .clip(androidx.compose.foundation.shape.CircleShape),
                alpha = 0.08f,
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )

            Box(modifier = Modifier.padding(padding)) {
                if (uiState != null) {
                    ScreenRenderer(screen = uiState!!, rendererFactory = rendererFactory)
                } else if (!isLoading) {
                    // Empty initial state
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Welcome to Next gen Banking",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.padding(32.dp)
                        )
                    }
                }
            }

            if (isLoading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (showChat) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = { showChat = false }) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                            Text(
                                "Banking Assistant",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            IconButton(onClick = { showInfo = !showInfo }) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = "Info",
                                    tint = if (showInfo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        if (showInfo) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
                                ),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Column(modifier = Modifier.padding(20.dp)) {
                                    Text(
                                        "How can I help you?",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                    val queries = listOf(
                                        "Show my balance",
                                        "Show spending analytics",
                                        "View recent transactions",
                                        "Pay my bills",
                                        "Send money",
                                        "Scan a QR code",
                                        "Go to home screen"
                                    )
                                    queries.forEach { query ->
                                        Text(
                                            "• $query",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding(vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            androidx.compose.material3.Checkbox(
                                checked = forceRefresh,
                                onCheckedChange = { forceRefresh = it }
                            )
                            Text("Need new UI (Force refresh AI)", style = MaterialTheme.typography.bodyMedium)
                        }

                        OutlinedTextField(
                            value = chatQuery,
                            onValueChange = { chatQuery = it },
                            placeholder = { Text("Ask me something...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            singleLine = true,
                            maxLines = 1,
                            shape = MaterialTheme.shapes.extraLarge,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (chatQuery.isNotBlank()) {
                                            viewModel.handleChatRequest(chatQuery, forceRefresh)
                                            showChat = false
                                            chatQuery = ""
                                            forceRefresh = false
                                        }
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier.padding(end = 4.dp)
                                ) {
                                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
                                }
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                            keyboardActions = KeyboardActions(onSend = {
                                if (chatQuery.isNotBlank()) {
                                    viewModel.handleChatRequest(chatQuery, forceRefresh)
                                    showChat = false
                                    chatQuery = ""
                                    forceRefresh = false
                                }
                            })
                        )
                    }
                }
            }
        }
    }
}
