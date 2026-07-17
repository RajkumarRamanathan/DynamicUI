package com.rajkumar.dynamic.core.renderer;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DefaultVisibilityEvaluator_Factory implements Factory<DefaultVisibilityEvaluator> {
  @Override
  public DefaultVisibilityEvaluator get() {
    return newInstance();
  }

  public static DefaultVisibilityEvaluator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DefaultVisibilityEvaluator newInstance() {
    return new DefaultVisibilityEvaluator();
  }

  private static final class InstanceHolder {
    static final DefaultVisibilityEvaluator_Factory INSTANCE = new DefaultVisibilityEvaluator_Factory();
  }
}
