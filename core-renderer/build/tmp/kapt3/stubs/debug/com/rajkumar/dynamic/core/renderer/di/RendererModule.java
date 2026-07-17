package com.rajkumar.dynamic.core.renderer.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\t\u001a\u00020\u0004H\u0007J\b\u0010\n\u001a\u00020\u0004H\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0007J\b\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\r\u001a\u00020\u0004H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J)\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\t\u0012\u00070\u0004\u00a2\u0006\u0002\b\u00140\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\b\u0010\u0017\u001a\u00020\u0004H\u0007J\b\u0010\u0018\u001a\u00020\u0004H\u0007J\b\u0010\u0019\u001a\u00020\u0004H\u0007J\b\u0010\u001a\u001a\u00020\u0004H\u0007J\b\u0010\u001b\u001a\u00020\u0016H\u0007\u00a8\u0006\u001c"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/di/RendererModule;", "", "()V", "provideBalanceCardRenderer", "Lcom/rajkumar/dynamic/core/renderer/WidgetRenderer;", "actionHandler", "Lcom/rajkumar/dynamic/core/renderer/ActionHandler;", "provideBillCardRenderer", "provideButtonRenderer", "provideColumnRenderer", "provideLazyColumnRenderer", "provideLottieRenderer", "providePayeeItemRenderer", "provideProgressItemRenderer", "provideQuickActionRenderer", "provideRendererFactory", "Lcom/rajkumar/dynamic/core/renderer/RendererFactory;", "renderers", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "visibilityEvaluator", "Lcom/rajkumar/dynamic/core/renderer/VisibilityEvaluator;", "provideRowRenderer", "provideShimmerRenderer", "provideTextRenderer", "provideTransactionItemRenderer", "provideVisibilityEvaluator", "core-renderer_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class RendererModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.rajkumar.dynamic.core.renderer.di.RendererModule INSTANCE = null;
    
    private RendererModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.VisibilityEvaluator provideVisibilityEvaluator() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.RendererFactory provideRendererFactory(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.rajkumar.dynamic.core.renderer.WidgetRenderer> renderers, @org.jetbrains.annotations.NotNull()
    com.rajkumar.dynamic.core.renderer.VisibilityEvaluator visibilityEvaluator) {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "text")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideTextRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "column")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideColumnRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "row")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideRowRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "lazy_column")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideLazyColumnRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "transaction_item")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideTransactionItemRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "progress_item")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideProgressItemRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "shimmer")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideShimmerRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "balance_card")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideBalanceCardRenderer(@org.jetbrains.annotations.NotNull()
    com.rajkumar.dynamic.core.renderer.ActionHandler actionHandler) {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "quick_action")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideQuickActionRenderer(@org.jetbrains.annotations.NotNull()
    com.rajkumar.dynamic.core.renderer.ActionHandler actionHandler) {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "lottie")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideLottieRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "button")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideButtonRenderer(@org.jetbrains.annotations.NotNull()
    com.rajkumar.dynamic.core.renderer.ActionHandler actionHandler) {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "bill_card")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer provideBillCardRenderer() {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoMap()
    @dagger.multibindings.StringKey(value = "payee_item")
    @org.jetbrains.annotations.NotNull()
    public final com.rajkumar.dynamic.core.renderer.WidgetRenderer providePayeeItemRenderer() {
        return null;
    }
}