package com.softeen.flashfreed.data.repository

import com.google.firebase.auth.FirebaseUser
import com.softeen.flashfreed.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createOrUpdateProfile(user: FirebaseUser)
    fun getProfile(uid: String): Flow<UserProfile?>
}