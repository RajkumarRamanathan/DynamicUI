package com.anju.dynamic.core.renderer;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\bH\u0002J&\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\bH\u0016\u00a8\u0006\u000b"}, d2 = {"Lcom/anju/dynamic/core/renderer/DefaultVisibilityEvaluator;", "Lcom/anju/dynamic/core/renderer/VisibilityEvaluator;", "()V", "evaluateSingleCondition", "", "condition", "", "context", "", "isVisible", "expression", "core-renderer_debug"})
public final class DefaultVisibilityEvaluator implements com.anju.dynamic.core.renderer.VisibilityEvaluator {
    
    @javax.inject.Inject()
    public DefaultVisibilityEvaluator() {
        super();
    }
    
    @java.lang.Override()
    public boolean isVisible(@org.jetbrains.annotations.Nullable()
    java.lang.String expression, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> context) {
        return false;
    }
    
    private final boolean evaluateSingleCondition(java.lang.String condition, java.util.Map<java.lang.String, java.lang.String> context) {
        return false;
    }
}