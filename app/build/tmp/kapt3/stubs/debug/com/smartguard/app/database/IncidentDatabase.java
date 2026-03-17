package com.smartguard.app.database;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/smartguard/app/database/IncidentDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "incidentDao", "Lcom/smartguard/app/database/IncidentDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.smartguard.app.database.Incident.class}, version = 1, exportSchema = false)
public abstract class IncidentDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.smartguard.app.database.IncidentDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartguard.app.database.IncidentDatabase.Companion Companion = null;
    
    public IncidentDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.smartguard.app.database.IncidentDao incidentDao();
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/smartguard/app/database/IncidentDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/smartguard/app/database/IncidentDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smartguard.app.database.IncidentDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}