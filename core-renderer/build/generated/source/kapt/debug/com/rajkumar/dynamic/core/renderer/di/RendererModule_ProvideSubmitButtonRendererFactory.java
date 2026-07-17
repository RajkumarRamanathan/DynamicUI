package com.rajkumar.dynamic.core.renderer.di;

import com.rajkumar.dynamic.core.renderer.ActionHandler;
import com.rajkumar.dynamic.core.renderer.WidgetRenderer;
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
public final class RendererModule_ProvideSubmitButtonRendererFactory implements Factory<WidgetRenderer> {
  private final Provider<ActionHandler> actionHandlerProvider;

  public RendererModule_ProvideSubmitButtonRendererFactory(
      Provider<ActionHandler> actionHandlerProvider) {
    this.actionHandlerProvider = actionHandlerProvider;
  }

  @Override
  public WidgetRenderer get() {
    return provideSubmitButtonRenderer(actionHandlerProvider.get());
  }

  public static RendererModule_ProvideSubmitButtonRendererFactory create(
      javax.inject.Provider<ActionHandler> actionHandlerProvider) {
    return new RendererModule_ProvideSubmitButtonRendererFactory(Providers.asDaggerProvider(actionHandlerProvider));
  }

  public static RendererModule_ProvideSubmitButtonRendererFactory create(
      Provider<ActionHandler> actionHandlerProvider) {
    return new RendererModule_ProvideSubmitButtonRendererFactory(actionHandlerProvider);
  }

  public static WidgetRenderer provideSubmitButtonRenderer(ActionHandler actionHandler) {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideSubmitButtonRenderer(actionHandler));
  }
}
