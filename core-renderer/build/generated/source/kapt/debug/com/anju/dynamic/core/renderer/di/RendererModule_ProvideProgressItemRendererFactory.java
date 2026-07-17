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
public final class RendererModule_ProvideProgressItemRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideProgressItemRenderer();
  }

  public static RendererModule_ProvideProgressItemRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideProgressItemRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideProgressItemRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideProgressItemRendererFactory INSTANCE = new RendererModule_ProvideProgressItemRendererFactory();
  }
}
