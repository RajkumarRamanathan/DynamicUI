package com.rajkumar.dynamic.data

import com.rajkumar.dynamic.core.model.Screen
import com.rajkumar.dynamic.core.network.NetworkModule
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DynamicRepository @Inject constructor() {
    private val baseUrl = "https://missiongiveback.in/dynamic_api/api/screen.php"

    suspend fun getScreen(id: String): Screen {
        return withContext(Dispatchers.IO) {
            try {
                NetworkModule.client.get("$baseUrl?id=$id&t=${System.currentTimeMillis()}").body()
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
}
