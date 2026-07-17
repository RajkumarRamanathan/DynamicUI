package com.rajkumar.dynamic.feature.analytics.di

import com.rajkumar.dynamic.core.renderer.WidgetRenderer
import com.rajkumar.dynamic.feature.analytics.AnalyticsChartRenderer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @IntoMap
    @StringKey("analytics_chart")
    fun provideAnalyticsChartRenderer(): WidgetRenderer = AnalyticsChartRenderer()
}
