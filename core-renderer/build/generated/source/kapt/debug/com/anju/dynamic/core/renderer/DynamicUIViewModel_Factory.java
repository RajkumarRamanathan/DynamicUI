package com.anju.dynamic.core.renderer;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DynamicUIViewModel_Factory implements Factory<DynamicUIViewModel> {
  @Override
  public DynamicUIViewModel get() {
    return newInstance();
  }

  public static DynamicUIViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DynamicUIViewModel newInstance() {
    return new DynamicUIViewModel();
  }

  private static final class InstanceHolder {
    static final DynamicUIViewModel_Factory INSTANCE = new DynamicUIViewModel_Factory();
  }
}
