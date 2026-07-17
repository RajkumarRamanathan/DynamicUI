package com.anju.dynamic.core.renderer.di;

import com.anju.dynamic.core.renderer.ActionHandler;
import com.anju.dynamic.core.renderer.WidgetRenderer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
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
public final class RendererModule_ProvideQuickActionRendererFactory implements Factory<WidgetRenderer> {
  private final Provider<ActionHandler> actionHandlerProvider;

  public RendererModule_ProvideQuickActionRendererFactory(
      Provider<ActionHandler> actionHandlerProvider) {
    this.actionHandlerProvider = actionHandlerProvider;
  }

  @Override
  public WidgetRenderer get() {
    return provideQuickActionRenderer(actionHandlerProvider.get());
  }

  public static RendererModule_ProvideQuickActionRendererFactory create(
      javax.inject.Provider<ActionHandler> actionHandlerProvider) {
    return new RendererModule_ProvideQuickActionRendererFactory(Providers.asDaggerProvider(actionHandlerProvider));
  }

  public static RendererModule_ProvideQuickActionRendererFactory create(
      Provider<ActionHandler> actionHandlerProvider) {
    return new RendererModule_ProvideQuickActionRendererFactory(actionHandlerProvider);
  }

  public static WidgetRenderer provideQuickActionRenderer(ActionHandler actionHandler) {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideQuickActionRenderer(actionHandler));
  }
}
