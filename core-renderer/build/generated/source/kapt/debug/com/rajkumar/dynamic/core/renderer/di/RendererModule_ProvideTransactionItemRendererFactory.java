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
public final class RendererModule_ProvideTransactionItemRendererFactory implements Factory<WidgetRenderer> {
  @Override
  public WidgetRenderer get() {
    return provideTransactionItemRenderer();
  }

  public static RendererModule_ProvideTransactionItemRendererFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WidgetRenderer provideTransactionItemRenderer() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideTransactionItemRenderer());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideTransactionItemRendererFactory INSTANCE = new RendererModule_ProvideTransactionItemRendererFactory();
  }
}
