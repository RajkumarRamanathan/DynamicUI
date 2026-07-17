package com.rajkumar.dynamic.di

import com.rajkumar.dynamic.core.model.Action
import com.rajkumar.dynamic.core.renderer.ActionHandler
import com.rajkumar.dynamic.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideActionHandler(
        navigationManager: NavigationManager,
        repository: com.rajkumar.dynamic.data.DynamicRepository
    ): ActionHandler {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        return object : ActionHandler {
            override fun handleAction(action: Action) {
                when (action.type) {
                    "navigate" -> {
                        val destination = action.payload?.get("destination")?.let {
                            if (it is JsonPrimitive) it.content else null
                        }
                        destination?.let { 
                            scope.launch {
                                navigationManager.navigate(it)
                            }
                        }
                    }
                    "submit_form" -> {
                        action.payload?.let { payload ->
                            scope.launch {
                                repository.submitForm(payload)
                            }
                        }
                    }
                    "generate_ai_form" -> {
                        val promptField = action.payload?.get("prompt_field")?.let {
                            if (it is kotlinx.serialization.json.JsonPrimitive) it.content else "ai_prompt"
                        } ?: "ai_prompt"
                        val formData = action.payload?.get("formData")?.let {
                            if (it is kotlinx.serialization.json.JsonObject) it else null
                        }
                        val prompt = formData?.get(promptField)?.let {
                            if (it is kotlinx.serialization.json.JsonPrimitive) it.content else null
                        }
                        if (!prompt.isNullOrBlank()) {
                            scope.launch {
                                navigationManager.navigate("ai/$prompt")
                            }
                        }
                    }
                    "create_page" -> {
                        val formData = action.payload?.get("formData")?.let {
                            if (it is kotlinx.serialization.json.JsonObject) it else null
                        }
                        val fieldsJson = formData?.get("form_builder_fields")?.let {
                            if (it is kotlinx.serialization.json.JsonPrimitive) it.content else null
                        }
                        val title = action.payload?.get("title")?.let {
                            if (it is kotlinx.serialization.json.JsonPrimitive) it.content else "Custom Form"
                        } ?: "Custom Form"

                        if (fieldsJson != null) {
                            val payload = kotlinx.serialization.json.buildJsonObject {
                                put("title", kotlinx.serialization.json.JsonPrimitive(title))
                                put("fields", kotlinx.serialization.json.JsonPrimitive(fieldsJson))
                            }
                            scope.launch {
                                val pageId = repository.createPage(payload)
                                if (pageId != null) {
                                    navigationManager.navigate(pageId)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
