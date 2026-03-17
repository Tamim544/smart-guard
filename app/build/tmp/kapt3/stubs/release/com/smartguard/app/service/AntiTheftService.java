package com.smartguard.app.service;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\"\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0082@\u00a2\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0015H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/smartguard/app/service/AntiTheftService;", "Landroidx/lifecycle/LifecycleService;", "<init>", "()V", "locationRepository", "Lcom/smartguard/app/repository/LocationRepository;", "cameraRepository", "Lcom/smartguard/app/repository/CameraRepository;", "alarmPlayer", "Lcom/smartguard/app/utils/AlarmPlayer;", "notificationHelper", "Lcom/smartguard/app/utils/NotificationHelper;", "database", "Lcom/smartguard/app/database/IncidentDatabase;", "connectivityHelper", "Lcom/smartguard/app/utils/ConnectivityHelper;", "emailSender", "Lcom/smartguard/app/utils/EmailSender;", "prefs", "Lcom/smartguard/app/utils/PreferencesManager;", "onCreate", "", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "executeAntiTheftProtocol", "triggerReason", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDestroy", "onBind", "Landroid/os/IBinder;", "Companion", "app_release"})
public final class AntiTheftService extends androidx.lifecycle.LifecycleService {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AntiTheftService";
    private static boolean isRunning = false;
    private com.smartguard.app.repository.LocationRepository locationRepository;
    private com.smartguard.app.repository.CameraRepository cameraRepository;
    private com.smartguard.app.utils.AlarmPlayer alarmPlayer;
    private com.smartguard.app.utils.NotificationHelper notificationHelper;
    private com.smartguard.app.database.IncidentDatabase database;
    private com.smartguard.app.utils.ConnectivityHelper connectivityHelper;
    private com.smartguard.app.utils.EmailSender emailSender;
    private com.smartguard.app.utils.PreferencesManager prefs;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartguard.app.service.AntiTheftService.Companion Companion = null;
    
    public AntiTheftService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final java.lang.Object executeAntiTheftProtocol(java.lang.String triggerReason, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
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
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/smartguard/app/service/AntiTheftService$Companion;", "", "<init>", "()V", "TAG", "", "isRunning", "", "()Z", "setRunning", "(Z)V", "app_release"})
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