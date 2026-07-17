package com.anju.dynamic.feature.analytics.di;

import com.anju.dynamic.core.renderer.WidgetRenderer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AnalyticsModule_ProvideAnalyticsChartRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideAnalyticsChartRenderer();
  }

  public static AnalyticsModule_ProvideAnalyticsChartRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideAnalyticsChartRenderer() {
    return Preconditions.checkNotNullFromProvides(AnalyticsModule.INSTANCE.provideAnalyticsChartRenderer());
  }

  private static final class InstanceHolder {
    static final AnalyticsModule_ProvideAnalyticsChartRendererFactory INSTANCE = new AnalyticsModule_ProvideAnalyticsChartRendererFactory();
  }
}
