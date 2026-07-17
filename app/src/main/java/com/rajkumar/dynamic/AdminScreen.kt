package com.rajkumar.dynamic

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    viewModel: DynamicViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    var newBalance by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf<String?>(null) }
    
    var newPageId by remember { mutableStateOf("") }
    var newPageTitle by remember { mutableStateOf("") }
    var newPageContent by remember { mutableStateOf("") }
    
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Dashboard") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Update Account Balance", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = newBalance,
                onValueChange = { newBalance = it },
                label = { Text("New Balance (e.g. 800000)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    scope.launch {
                        isSubmitting = true
                        val amount = newBalance.toDoubleOrNull() ?: 0.0
                        val success = viewModel.updateBalance(amount)
                        isSubmitting = false
                        if (success) {
                            successMessage = "Balance updated to ₹$amount successfully!"
                            newBalance = ""
                        } else {
                            successMessage = "Failed to update balance."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSubmitting && newBalance.isNotBlank()
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text("Update Database")
                }
            }

            if (successMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = successMessage!!,
                    color = if (successMessage!!.contains("successfully")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(32.dp))
            
            Text("Create Dynamic Page", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = newPageId,
                onValueChange = { newPageId = it },
                label = { Text("Page ID (e.g. loan)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            OutlinedTextField(
                value = newPageTitle,
                onValueChange = { newPageTitle = it },
                label = { Text("Page Title (e.g. Loan Apply)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            OutlinedTextField(
                value = newPageContent,
                onValueChange = { newPageContent = it },
                label = { Text("Page Content") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    scope.launch {
                        isSubmitting = true
                        val success = viewModel.createPage(newPageId, newPageTitle, newPageContent)
                        isSubmitting = false
                        if (success) {
                            successMessage = "Page '$newPageId' created successfully!"
                            newPageId = ""
                            newPageTitle = ""
                            newPageContent = ""
                        } else {
                            successMessage = "Failed to create page."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSubmitting && newPageId.isNotBlank() && newPageTitle.isNotBlank() && newPageContent.isNotBlank()
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text("Create Page")
                }
            }
        }
    }
}
