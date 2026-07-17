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
                }
            }
        }
    }
}
