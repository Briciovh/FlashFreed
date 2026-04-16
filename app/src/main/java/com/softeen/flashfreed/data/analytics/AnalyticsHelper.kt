package com.softeen.flashfreed.data.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import jakarta.inject.Inject

class AnalyticsHelper @Inject constructor(
    private val analytics: FirebaseAnalytics
) {
    fun logLogin(method: String = "google") {
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param(FirebaseAnalytics.Param.METHOD, method)
        }
    }

    fun logPostCreated(hasImage: Boolean) {
        analytics.logEvent("post_created") {
            param("has_image", hasImage.toString())
        }
    }

    fun logLikeToggled(liked: Boolean) {
        analytics.logEvent("like_toggled") {
            param("action", if (liked) "liked" else "unliked")
        }
    }

    fun logScreenView(screenName: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)
        }
    }

    fun setUserProperty(uid: String) {
        analytics.setUserId(uid)
    }
}