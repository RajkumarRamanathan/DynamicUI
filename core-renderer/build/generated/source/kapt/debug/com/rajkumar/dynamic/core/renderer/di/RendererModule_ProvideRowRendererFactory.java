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
public final class RendererModule_ProvideRowRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideRowRenderer();
  }

  public static RendererModule_ProvideRowRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideRowRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideRowRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideRowRendererFactory INSTANCE = new RendererModule_ProvideRowRendererFactory();
  }
}
