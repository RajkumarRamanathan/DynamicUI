package com.rajkumar.dynamic.core.renderer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J)\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\r\u00a2\u0006\u0002\u0010\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadScreen", "", "screenProvider", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lcom/rajkumar/dynamic/core/model/Screen;", "", "(Lkotlin/jvm/functions/Function1;)V", "UiState", "core-renderer_debug"})
public class DynamicUIViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState> uiState = null;
    
    @javax.inject.Inject()
    public DynamicUIViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState> getUiState() {
        return null;
    }
    
    public final void loadScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super com.rajkumar.dynamic.core.model.Screen>, ? extends java.lang.Object> screenProvider) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState;", "", "()V", "Error", "Loading", "Success", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState$Error;", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState$Loading;", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState$Success;", "core-renderer_debug"})
    public static abstract class UiState {
        
        private UiState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState$Error;", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core-renderer_debug"})
        public static final class Error extends com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState$Loading;", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState;", "()V", "core-renderer_debug"})
        public static final class Loading extends com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            public static final com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState$Success;", "Lcom/rajkumar/dynamic/core/renderer/DynamicUIViewModel$UiState;", "screen", "Lcom/rajkumar/dynamic/core/model/Screen;", "(Lcom/rajkumar/dynamic/core/model/Screen;)V", "getScreen", "()Lcom/rajkumar/dynamic/core/model/Screen;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core-renderer_debug"})
        public static final class Success extends com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            private final com.rajkumar.dynamic.core.model.Screen screen = null;
            
            @org.jetbrains.annotations.NotNull()
            public final com.rajkumar.dynamic.core.model.Screen component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.rajkumar.dynamic.core.renderer.DynamicUIViewModel.UiState.Success copy(@org.jetbrains.annotations.NotNull()
            com.rajkumar.dynamic.core.model.Screen screen) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
            
            public Success(@org.jetbrains.annotations.NotNull()
            com.rajkumar.dynamic.core.model.Screen screen) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.rajkumar.dynamic.core.model.Screen getScreen() {
                return null;
            }
        }
    }
}