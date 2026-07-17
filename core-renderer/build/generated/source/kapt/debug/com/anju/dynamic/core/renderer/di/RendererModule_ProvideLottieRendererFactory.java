package com.anju.dynamic.core.renderer.di;

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
public final class RendererModule_ProvideLottieRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideLottieRenderer();
  }

  public static RendererModule_ProvideLottieRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideLottieRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideLottieRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideLottieRendererFactory INSTANCE = new RendererModule_ProvideLottieRendererFactory();
  }
}
