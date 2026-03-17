package com.smartguard.app.utils;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000 %2\u00020\u0001:\u0001%B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010$\u001a\u00020\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00128F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00188F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00188F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR$\u0010!\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00188F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001d\u00a8\u0006&"}, d2 = {"Lcom/smartguard/app/utils/PreferencesManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "value", "", "alertEmail", "getAlertEmail", "()Ljava/lang/String;", "setAlertEmail", "(Ljava/lang/String;)V", "gmailAppPassword", "getGmailAppPassword", "setGmailAppPassword", "", "failedAttemptsThreshold", "getFailedAttemptsThreshold", "()I", "setFailedAttemptsThreshold", "(I)V", "", "antiTheftEnabled", "getAntiTheftEnabled", "()Z", "setAntiTheftEnabled", "(Z)V", "motionProtectionEnabled", "getMotionProtectionEnabled", "setMotionProtectionEnabled", "shutdownProtectionEnabled", "getShutdownProtectionEnabled", "setShutdownProtectionEnabled", "isEmailConfigured", "Companion", "app_release"})
public final class PreferencesManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "smartguard_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_EMAIL = "alert_email";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_APP_PASSWORD = "gmail_app_password";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FAILED_ATTEMPTS_THRESHOLD = "failed_attempts_threshold";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ANTI_THEFT_ENABLED = "anti_theft_enabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_MOTION_PROTECTION_ENABLED = "motion_protection_enabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SHUTDOWN_PROTECTION_ENABLED = "shutdown_protection_enabled";
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartguard.app.utils.PreferencesManager.Companion Companion = null;
    
    public PreferencesManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAlertEmail() {
        return null;
    }
    
    public final void setAlertEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGmailAppPassword() {
        return null;
    }
    
    public final void setGmailAppPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final int getFailedAttemptsThreshold() {
        return 0;
    }
    
    public final void setFailedAttemptsThreshold(int value) {
    }
    
    public final boolean getAntiTheftEnabled() {
        return false;
    }
    
    public final void setAntiTheftEnabled(boolean value) {
    }
    
    public final boolean getMotionProtectionEnabled() {
        return false;
    }
    
    public final void setMotionProtectionEnabled(boolean value) {
    }
    
    public final boolean getShutdownProtectionEnabled() {
        return false;
    }
    
    public final void setShutdownProtectionEnabled(boolean value) {
    }
    
    public final boolean isEmailConfigured() {
        return false;
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/smartguard/app/utils/PreferencesManager$Companion;", "", "<init>", "()V", "PREFS_NAME", "", "KEY_EMAIL", "KEY_APP_PASSWORD", "KEY_FAILED_ATTEMPTS_THRESHOLD", "KEY_ANTI_THEFT_ENABLED", "KEY_MOTION_PROTECTION_ENABLED", "KEY_SHUTDOWN_PROTECTION_ENABLED", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}