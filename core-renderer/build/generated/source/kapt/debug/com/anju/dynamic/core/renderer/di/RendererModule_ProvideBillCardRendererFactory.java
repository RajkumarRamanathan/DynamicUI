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
public final class RendererModule_ProvideBillCardRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideBillCardRenderer();
  }

  public static RendererModule_ProvideBillCardRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideBillCardRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideBillCardRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideBillCardRendererFactory INSTANCE = new RendererModule_ProvideBillCardRendererFactory();
  }
}
