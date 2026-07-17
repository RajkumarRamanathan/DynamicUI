package com.anju.dynamic.core.renderer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/anju/dynamic/core/renderer/VisibilityEvaluator;", "", "isVisible", "", "expression", "", "context", "", "core-renderer_debug"})
public abstract interface VisibilityEvaluator {
    
    public abstract boolean isVisible(@org.jetbrains.annotations.Nullable()
    java.lang.String expression, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> context);
}