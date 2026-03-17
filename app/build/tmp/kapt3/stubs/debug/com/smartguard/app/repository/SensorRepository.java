package com.smartguard.app.repository;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\u0012\u0010\u001a\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001a\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/smartguard/app/repository/SensorRepository;", "Landroid/hardware/SensorEventListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "sensorManager", "Landroid/hardware/SensorManager;", "accelerometer", "Landroid/hardware/Sensor;", "gyroscope", "proximity", "_motionDetected", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "motionDetected", "Lkotlinx/coroutines/flow/SharedFlow;", "getMotionDetected", "()Lkotlinx/coroutines/flow/SharedFlow;", "sensitivity", "", "setSensitivity", "", "value", "startListening", "stopListening", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "onAccuracyChanged", "sensor", "accuracy", "", "app_debug"})
public final class SensorRepository implements android.hardware.SensorEventListener {
    @org.jetbrains.annotations.NotNull()
    private final android.hardware.SensorManager sensorManager = null;
    @org.jetbrains.annotations.Nullable()
    private final android.hardware.Sensor accelerometer = null;
    @org.jetbrains.annotations.Nullable()
    private final android.hardware.Sensor gyroscope = null;
    @org.jetbrains.annotations.Nullable()
    private final android.hardware.Sensor proximity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.String> _motionDetected = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<java.lang.String> motionDetected = null;
    private float sensitivity = 15.0F;
    
    public SensorRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<java.lang.String> getMotionDetected() {
        return null;
    }
    
    public final void setSensitivity(float value) {
    }
    
    public final void startListening() {
    }
    
    public final void stopListening() {
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor, int accuracy) {
    }
}