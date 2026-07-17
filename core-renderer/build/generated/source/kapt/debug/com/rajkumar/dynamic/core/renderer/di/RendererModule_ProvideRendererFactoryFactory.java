package com.rajkumar.dynamic.core.renderer.di;

import com.rajkumar.dynamic.core.renderer.RendererFactory;
import com.rajkumar.dynamic.core.renderer.VisibilityEvaluator;
import com.rajkumar.dynamic.core.renderer.WidgetRenderer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RendererModule_ProvideRendererFactoryFactory implements Factory<RendererFactory> {
  private final Provider<Map<String, WidgetRenderer>> renderersProvider;

  private final Provider<VisibilityEvaluator> visibilityEvaluatorProvider;

  public RendererModule_ProvideRendererFactoryFactory(
      Provider<Map<String, WidgetRenderer>> renderersProvider,
      Provider<VisibilityEvaluator> visibilityEvaluatorProvider) {
    this.renderersProvider = renderersProvider;
    this.visibilityEvaluatorProvider = visibilityEvaluatorProvider;
  }

  @Override
  public RendererFactory get() {
    return provideRendererFactory(renderersProvider.get(), visibilityEvaluatorProvider.get());
  }

  public static RendererModule_ProvideRendererFactoryFactory create(
      javax.inject.Provider<Map<String, WidgetRenderer>> renderersProvider,
      javax.inject.Provider<VisibilityEvaluator> visibilityEvaluatorProvider) {
    return new RendererModule_ProvideRendererFactoryFactory(Providers.asDaggerProvider(renderersProvider), Providers.asDaggerProvider(visibilityEvaluatorProvider));
  }

  public static RendererModule_ProvideRendererFactoryFactory create(
      Provider<Map<String, WidgetRenderer>> renderersProvider,
      Provider<VisibilityEvaluator> visibilityEvaluatorProvider) {
    return new RendererModule_ProvideRendererFactoryFactory(renderersProvider, visibilityEvaluatorProvider);
  }

  public static RendererFactory provideRendererFactory(Map<String, WidgetRenderer> renderers,
      VisibilityEvaluator visibilityEvaluator) {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideRendererFactory(renderers, visibilityEvaluator));
  }
}
