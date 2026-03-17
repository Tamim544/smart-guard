package com.smartguard.app.utils;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/smartguard/app/utils/NotificationHelper;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "createNotificationChannels", "", "getMainPendingIntent", "Landroid/app/PendingIntent;", "getForegroundNotification", "Landroid/app/Notification;", "getAntiTheftNotification", "getShutdownProtectionNotification", "getAlertNotification", "message", "", "Companion", "app_release"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "smartguard_channel";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ALERT_CHANNEL_ID = "smartguard_alert_channel";
    public static final int NOTIFICATION_ID = 1;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartguard.app.utils.NotificationHelper.Companion Companion = null;
    
    public NotificationHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void createNotificationChannels() {
    }
    
    private final android.app.PendingIntent getMainPendingIntent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification getForegroundNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification getAntiTheftNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification getShutdownProtectionNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification getAlertNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/smartguard/app/utils/NotificationHelper$Companion;", "", "<init>", "()V", "CHANNEL_ID", "", "ALERT_CHANNEL_ID", "NOTIFICATION_ID", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}