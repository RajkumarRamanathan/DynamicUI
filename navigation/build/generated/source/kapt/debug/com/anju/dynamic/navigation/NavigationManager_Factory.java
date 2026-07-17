package com.anju.dynamic.navigation;

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
public final class NavigationManager_Factory implements Factory<NavigationManager> {
  @Override
  public NavigationManager get() {
    return newInstance();
  }

  public static NavigationManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NavigationManager newInstance() {
    return new NavigationManager();
  }

  private static final class InstanceHolder {
    static final NavigationManager_Factory INSTANCE = new NavigationManager_Factory();
  }
}
