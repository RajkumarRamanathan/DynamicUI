package com.rajkumar.dynamic.core.renderer.di;

import com.rajkumar.dynamic.core.renderer.VisibilityEvaluator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RendererModule_ProvideVisibilityEvaluatorFactory implements Factory<VisibilityEvaluator> {
  @Override
  public VisibilityEvaluator get() {
    return provideVisibilityEvaluator();
  }

  public static RendererModule_ProvideVisibilityEvaluatorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static VisibilityEvaluator provideVisibilityEvaluator() {
    return Preconditions.checkNotNullFromProvides(RendererModule.INSTANCE.provideVisibilityEvaluator());
  }

  private static final class InstanceHolder {
    static final RendererModule_ProvideVisibilityEvaluatorFactory INSTANCE = new RendererModule_ProvideVisibilityEvaluatorFactory();
  }
}
