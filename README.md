# ⚡ FlashFreed

FlashFreed is a modern, high-performance mini social network hub built with **Jetpack Compose** and **Firebase**. It showcases a robust implementation of **Clean Architecture** and **MVVM**, designed to be scalable, testable, and maintainable.

## 🚀 Key Features

*   **📱 Real-time Social Feed:** Browse and interact with posts instantly powered by Cloud Firestore.
*   **✍️ Dynamic Content Creation:** Share your thoughts and photos with a seamless post creation flow using Firebase Storage.
*   **🔐 Modern Authentication:** Secure login experience via **Google ID** and **Credential Manager** integrated with Firebase Auth.
*   **👤 Rich Profiles:** Personalized user profiles with real-time updates.
*   **🔔 Push Notifications:** Stay engaged with Firebase Cloud Messaging (FCM).
*   **📊 Advanced Observability:** Integrated **Crashlytics** and **Analytics** for performance monitoring and user insights.

## 🛠️ Tech Stack & Architecture

*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose (Material 3)
*   **Architecture:** Clean Architecture + MVVM (Model-View-ViewModel)
*   **Dependency Injection:** Hilt (Dagger)
*   **Backend:** Firebase (Auth, Firestore, Storage, Messaging)
*   **Asynchronous Flow:** Kotlin Coroutines & Flow
*   **Image Loading:** Coil 3
*   **Navigation:** Jetpack Compose Navigation with Hilt integration
*   **Build System:** Gradle Kotlin DSL (KTS) with Version Catalog

## 🏗️ Showcase Highlights

*   **Clean Architecture:** Clear separation of concerns between `Data`, `Domain`, and `UI` layers.
*   **State Management:** Leveraging `StateFlow` and `LaunchedEffect` for predictable UI states.
*   **Modern Auth Flow:** Demonstrates the implementation of the new `androidx.credentials` library for seamless Google Sign-In.
*   **Analytics-Driven:** Built-in tracking for key user actions and automated crash reporting.

---
*Built with ❤️ using the latest Android development standards.*
