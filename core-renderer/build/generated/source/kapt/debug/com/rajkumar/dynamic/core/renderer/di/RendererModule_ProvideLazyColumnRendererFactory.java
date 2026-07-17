package com.rajkumar.dynamic.core.renderer.di;

import com.rajkumar.dynamic.core.renderer.WidgetRenderer;
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
public final class RendererModule_ProvideLazyColumnRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideLazyColumnRenderer();
  }

  public static RendererModule_ProvideLazyColumnRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideLazyColumnRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideLazyColumnRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideLazyColumnRendererFactory INSTANCE = new RendererModule_ProvideLazyColumnRendererFactory();
  }
}
