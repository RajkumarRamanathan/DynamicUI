package com.rajkumar.dynamic.data

import com.rajkumar.dynamic.core.model.Screen
import com.rajkumar.dynamic.core.network.NetworkModule
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DynamicRepository @Inject constructor(private val userManager: UserManager) {
    private val baseUrl = "https://missiongiveback.in/dynamic_api/api/screen.php"

    suspend fun getScreen(id: String): Screen {
        return withContext(Dispatchers.IO) {
            try {
                val userName = userManager.getUserName() ?: "User"
                val userId = userManager.getUserId()
                val role = userManager.getRole()
                val encodedUserName = java.net.URLEncoder.encode(userName, "UTF-8")
                NetworkModule.client.get("$baseUrl?id=$id&t=${System.currentTimeMillis()}&user_name=$encodedUserName&user_id=$userId&role=$role").body()
            } catch (e: Exception) {
                e.printStackTrace()
                Screen(
                    id = "error",
                    title = "Network Error",
                    content = com.rajkumar.dynamic.core.model.Widget(
                        type = "text",
                        properties = kotlinx.serialization.json.buildJsonObject {
                            put("text", kotlinx.serialization.json.JsonPrimitive(e.localizedMessage ?: "Failed to connect to server"))
                        }
                    )
                )
            }
        }
    }

    suspend fun getAiScreen(prompt: String, forceRefresh: Boolean = false): Screen {
        return withContext(Dispatchers.IO) {
            try {
                // URL encode the prompt and username
                val encodedPrompt = java.net.URLEncoder.encode(prompt, "UTF-8")
                val userName = userManager.getUserName() ?: "Alpha Investor"
                val encodedUserName = java.net.URLEncoder.encode(userName, "UTF-8")
                val aiUrl = "https://missiongiveback.in/dynamic_api/api/ai_screen.php?prompt=$encodedPrompt&refresh=$forceRefresh&user_name=$encodedUserName"
                NetworkModule.client.get(aiUrl).body()
            } catch (e: Exception) {
                e.printStackTrace()
                Screen(
                    id = "error",
                    title = "AI Error",
                    content = com.rajkumar.dynamic.core.model.Widget(
                        type = "text",
                        properties = kotlinx.serialization.json.buildJsonObject {
                            put("text", kotlinx.serialization.json.JsonPrimitive("Failed to generate UI: ${e.localizedMessage}"))
                        }
                    )
                )
            }
        }
    }

    suspend fun updateBalance(newBalance: Double): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val adminUrl = "https://missiongiveback.in/dynamic_api/api/admin_update.php"
                val response = NetworkModule.client.post(adminUrl) {
                    header("Content-Type", "application/json")
                    setBody(kotlinx.serialization.json.buildJsonObject {
                        put("balance", kotlinx.serialization.json.JsonPrimitive(newBalance))
                    })
                }
                response.status.value in 200..299
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    suspend fun createPage(pageId: String, title: String, content: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val adminUrl = "https://missiongiveback.in/dynamic_api/api/admin_create_page.php"
                val response = NetworkModule.client.post(adminUrl) {
                    header("Content-Type", "application/json")
                    setBody(kotlinx.serialization.json.buildJsonObject {
                        put("page_id", kotlinx.serialization.json.JsonPrimitive(pageId))
                        put("title", kotlinx.serialization.json.JsonPrimitive(title))
                        put("content", kotlinx.serialization.json.JsonPrimitive(content))
                    })
                }
                response.status.value in 200..299
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
    suspend fun submitForm(payload: kotlinx.serialization.json.JsonObject): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val submitUrl = "https://missiongiveback.in/dynamic_api/api/submit_form.php"
                val response = NetworkModule.client.post(submitUrl) {
                    header("Content-Type", "application/json")
                    setBody(payload)
                }
                response.status.value in 200..299
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
    
    suspend fun createPage(payload: kotlinx.serialization.json.JsonObject): String? {
        return withContext(Dispatchers.IO) {
            try {
                val url = "https://missiongiveback.in/dynamic_api/api/create_page.php"
                val response = NetworkModule.client.post(url) {
                    header("Content-Type", "application/json")
                    setBody(payload)
                }
                if (response.status.value in 200..299) {
                    val responseText = response.bodyAsText()
                    val json = kotlinx.serialization.json.Json.parseToJsonElement(responseText).jsonObject
                    if (json["status"]?.jsonPrimitive?.content == "success") {
                        json["page_id"]?.jsonPrimitive?.content
                    } else null
                } else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
