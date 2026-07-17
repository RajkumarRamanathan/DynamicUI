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
public final class RendererModule_ProvideInputCheckboxRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideInputCheckboxRenderer();
  }

  public static RendererModule_ProvideInputCheckboxRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideInputCheckboxRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideInputCheckboxRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideInputCheckboxRendererFactory INSTANCE = new RendererModule_ProvideInputCheckboxRendererFactory();
  }
}
