package com.smartguard.app.service;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/smartguard/app/service/ShutdownPreventionService;", "Landroidx/lifecycle/LifecycleService;", "<init>", "()V", "shutdownReceiver", "Landroid/content/BroadcastReceiver;", "notificationHelper", "Lcom/smartguard/app/utils/NotificationHelper;", "onCreate", "", "registerShutdownReceiver", "onDestroy", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "Companion", "app_release"})
public final class ShutdownPreventionService extends androidx.lifecycle.LifecycleService {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "ShutdownPrevention";
    private static boolean isRunning = false;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver shutdownReceiver;
    private com.smartguard.app.utils.NotificationHelper notificationHelper;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartguard.app.service.ShutdownPreventionService.Companion Companion = null;
    
    public ShutdownPreventionService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void registerShutdownReceiver() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/smartguard/app/service/ShutdownPreventionService$Companion;", "", "<init>", "()V", "TAG", "", "isRunning", "", "()Z", "setRunning", "(Z)V", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isRunning() {
            return false;
        }
        
        public final void setRunning(boolean p0) {
        }
    }
}