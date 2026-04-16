package com.softeen.flashfreed.data.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.snapshots
import com.softeen.flashfreed.data.model.UserProfile
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {

    private val usersCollection = firestore.collection("users")

    override suspend fun createOrUpdateProfile(user: FirebaseUser) {
        val profile = UserProfile(
            uid = user.uid,
            displayName = user.displayName ?: "",
            email = user.email ?: "",
            photoUrl = user.photoUrl?.toString() ?: ""
        )
        usersCollection
            .document(user.uid)
            .set(profile, SetOptions.merge())
            .await()
    }

    override fun getProfile(uid: String): Flow<UserProfile?> =
        usersCollection.document(uid)
            .snapshots()
            .map { it.toObject(UserProfile::class.java) }
}