package com.rajkumar.dynamic

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onLoginSuccess: (Int, String, String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Welcome to Banking App") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login or Register",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = username,
                onValueChange = { 
                    username = it
                    errorMessage = null
                },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = password,
                onValueChange = { 
                    password = it
                    errorMessage = null
                },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = {
                    if (username.isNotBlank() && password.isNotBlank()) {
                        isLoading = true
                        errorMessage = null
                        coroutineScope.launch {
                            try {
                                val result = withContext(Dispatchers.IO) {
                                    val url = URL("https://missiongiveback.in/dynamic_api/api/login.php")
                                    val connection = url.openConnection() as HttpURLConnection
                                    connection.requestMethod = "POST"
                                    connection.setRequestProperty("Content-Type", "application/json")
                                    connection.doOutput = true
                                    
                                    val payload = JSONObject().apply {
                                        put("username", username)
                                        put("password", password)
                                    }.toString()
                                    
                                    OutputStreamWriter(connection.outputStream).use { it.write(payload) }
                                    
                                    val responseCode = connection.responseCode
                                    if (responseCode == HttpURLConnection.HTTP_OK) {
                                        val response = connection.inputStream.bufferedReader().readText()
                                        JSONObject(response)
                                    } else {
                                        null
                                    }
                                }
                                
                                isLoading = false
                                if (result != null && result.getBoolean("success")) {
                                    val userId = result.getInt("user_id")
                                    val role = result.getString("role")
                                    onLoginSuccess(userId, username, role)
                                } else {
                                    errorMessage = result?.optString("message", "Login failed") ?: "Network error"
                                }
                            } catch (e: Exception) {
                                isLoading = false
                                errorMessage = e.message ?: "An error occurred"
                            }
                        }
                    } else {
                        errorMessage = "Please enter username and password"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Login")
                }
            }
        }
    }
}
