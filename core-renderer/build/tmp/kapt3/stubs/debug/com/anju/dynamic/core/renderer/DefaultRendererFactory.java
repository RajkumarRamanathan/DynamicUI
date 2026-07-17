package com.anju.dynamic.core.renderer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B7\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0017J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0016R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/anju/dynamic/core/renderer/DefaultRendererFactory;", "Lcom/anju/dynamic/core/renderer/RendererFactory;", "renderers", "", "", "Lcom/anju/dynamic/core/renderer/WidgetRenderer;", "visibilityEvaluator", "Lcom/anju/dynamic/core/renderer/VisibilityEvaluator;", "context", "(Ljava/util/Map;Lcom/anju/dynamic/core/renderer/VisibilityEvaluator;Ljava/util/Map;)V", "RenderWidget", "", "widget", "Lcom/anju/dynamic/core/model/Widget;", "getContext", "core-renderer_debug"})
public final class DefaultRendererFactory implements com.anju.dynamic.core.renderer.RendererFactory {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.anju.dynamic.core.renderer.WidgetRenderer> renderers = null;
    @org.jetbrains.annotations.NotNull()
    private final com.anju.dynamic.core.renderer.VisibilityEvaluator visibilityEvaluator = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.String> context = null;
    
    public DefaultRendererFactory(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends com.anju.dynamic.core.renderer.WidgetRenderer> renderers, @org.jetbrains.annotations.NotNull()
    com.anju.dynamic.core.renderer.VisibilityEvaluator visibilityEvaluator, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> context) {
        super();
    }
    
    @java.lang.Override()
    @androidx.compose.runtime.Composable()
    public void RenderWidget(@org.jetbrains.annotations.NotNull()
    com.anju.dynamic.core.model.Widget widget) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.Map<java.lang.String, java.lang.String> getContext() {
        return null;
    }
}