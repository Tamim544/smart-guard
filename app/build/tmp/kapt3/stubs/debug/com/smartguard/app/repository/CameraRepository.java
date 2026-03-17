package com.smartguard.app.repository;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0082@\u00a2\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/smartguard/app/repository/CameraRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "imageCapture", "Landroidx/camera/core/ImageCapture;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "takePhoto", "Landroid/net/Uri;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shutdown", "", "app_debug"})
public final class CameraRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.core.ImageCapture imageCapture;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ExecutorService cameraExecutor = null;
    
    public CameraRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object takePhoto(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.net.Uri> $completion) {
        return null;
    }
    
    private final java.lang.Object getCameraProvider(kotlin.coroutines.Continuation<? super androidx.camera.lifecycle.ProcessCameraProvider> $completion) {
        return null;
    }
    
    public final void shutdown() {
    }
}