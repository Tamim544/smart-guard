package com.smartguard.app.utils;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0002\u00a8\u0006\u0010"}, d2 = {"Lcom/smartguard/app/utils/EmailSender;", "", "<init>", "()V", "sendAlertEmail", "", "senderEmail", "", "appPassword", "recipientEmail", "location", "photoPath", "eventType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildEmailBody", "Companion", "app_release"})
public final class EmailSender {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "EmailSender";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SMTP_HOST = "smtp.gmail.com";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SMTP_PORT = "587";
    @org.jetbrains.annotations.NotNull()
    public static final com.smartguard.app.utils.EmailSender.Companion Companion = null;
    
    public EmailSender() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendAlertEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String senderEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String appPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String recipientEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String location, @org.jetbrains.annotations.Nullable()
    java.lang.String photoPath, @org.jetbrains.annotations.NotNull()
    java.lang.String eventType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final java.lang.String buildEmailBody(java.lang.String eventType, java.lang.String location) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/smartguard/app/utils/EmailSender$Companion;", "", "<init>", "()V", "TAG", "", "SMTP_HOST", "SMTP_PORT", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}