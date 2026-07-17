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
public final class RendererModule_ProvideTextRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideTextRenderer();
  }

  public static RendererModule_ProvideTextRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideTextRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideTextRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideTextRendererFactory INSTANCE = new RendererModule_ProvideTextRendererFactory();
  }
}
