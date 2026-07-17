package com.rajkumar.dynamic.core.renderer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H&\u00a8\u0006\t"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/RendererFactory;", "", "RenderWidget", "", "widget", "Lcom/rajkumar/dynamic/core/model/Widget;", "getContext", "", "", "core-renderer_debug"})
public abstract interface RendererFactory {
    
    @androidx.compose.runtime.Composable()
    public abstract void RenderWidget(@org.jetbrains.annotations.NotNull()
    com.rajkumar.dynamic.core.model.Widget widget);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Map<java.lang.String, java.lang.String> getContext();
}