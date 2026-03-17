# SmartGuard 🛡️

**SmartGuard** is a robust Android security application designed to protect your device and surroundings using intelligent motion detection and anti-theft protocols. When activated, it monitors for unauthorized access and automatically triggers a series of security protocols to capture evidence and alert you.

## 🚀 Key Features

-   **Intelligent Motion Detection**: Uses advanced sensor fusion to detect unauthorized movement (perfect for protecting your phone in a cafe or on a desk).
-   **Anti-Theft Protocol**: Automatically triggers when someone tries to guess your password.
    -   📸 **Intruder Photo**: Captures a front-facing camera snapshot of whoever is trying to unlock your phone.
    -   📍 **GPS Location**: Logs the exact coordinates of the breach.
    -   📧 **Email Alerts**: Sends the photo and location directly to your inbox via Gmail SMTP.
-   **Shutdown Prevention**: Detects attempts to power off the device and triggers emergency evidence capture.
-   **Audible Alarm**: Sounds a loud alarm to deter thieves and alert bystanders.
-   **Incident History**: A dedicated dashboard to review all security events, including photos, locations, and timestamps.
-   **Email Settings**: Configure your alert email and security thresholds directly in the app.

## 🛠️ Built With

-   **Kotlin**: Modern, expressive programming language.
-   **MVVM Architecture**: Structured for scalability and maintainability.
-   **JavaMail (SMTP)**: For direct email alerts without needing a backend server.
-   **Device Admin API**: For monitoring failed unlock attempts.
-   **Room Database**: Local storage for secure security logs.
-   **CameraX**: Seamless camera integration for evidence capture.
-   **Google Play Services Location**: High-accuracy location tracking.

## 📱 Getting Started

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/Tamim544/smart-guard.git
    ```
2.  **Open in Android Studio**.
3.  **Build and Run** on a physical Android device.
4.  **Setup**:
    -   Enable **Device Admin** from the main screen.
    -   Go to **Settings** and configure your Gmail App Password for alerts.

## 🔐 Permissions Required

-   **Device Administrator**: To monitor failed unlock attempts.
-   **Camera**: To take evidence photos.
-   **Location**: To record where incidents happen.
-   **Internet**: To send email alerts.
-   **Notifications**: For real-time security status (Android 13+).
-   **Sensors**: For motion detection.

---
Developed as a complete mobile security solution.
