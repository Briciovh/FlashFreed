package com.softeen.flashfreed.data.analytics

import com.google.firebase.crashlytics.FirebaseCrashlytics
import jakarta.inject.Inject

class CrashlyticsHelper @Inject constructor() {

    fun setUser(uid: String) {
        FirebaseCrashlytics.getInstance().setUserId(uid)
    }

    fun logError(message: String, throwable: Throwable? = null) {
        FirebaseCrashlytics.getInstance().log(message)
        throwable?.let {
            FirebaseCrashlytics.getInstance().recordException(it)
        }
    }

    fun setScreen(screenName: String) {
        FirebaseCrashlytics.getInstance()
            .setCustomKey("last_screen", screenName)
    }
}