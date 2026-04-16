package com.softeen.flashfreed.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val currentUser: StateFlow<FirebaseUser?>
    suspend fun signInWithGoogle(context: Context): Result<FirebaseUser>
    suspend fun signOut()
}