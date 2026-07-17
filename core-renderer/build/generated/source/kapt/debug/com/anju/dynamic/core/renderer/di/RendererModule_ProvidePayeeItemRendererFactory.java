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
public final class RendererModule_ProvidePayeeItemRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return providePayeeItemRenderer();
  }

  public static RendererModule_ProvidePayeeItemRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer providePayeeItemRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.providePayeeItemRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvidePayeeItemRendererFactory INSTANCE = new RendererModule_ProvidePayeeItemRendererFactory();
  }
}
