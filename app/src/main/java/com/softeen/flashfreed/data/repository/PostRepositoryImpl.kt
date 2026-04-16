package com.softeen.flashfreed.data.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import com.softeen.flashfreed.data.model.Post
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : PostRepository {

    private val postsCollection = firestore.collection("posts")

    override suspend fun createPost(
        content: String,
        user: FirebaseUser
    ): Result<Unit> = try {
        val docRef = postsCollection.document() // genera ID automático
        val post = Post(
            id = docRef.id,
            authorUid = user.uid,
            authorName = user.displayName ?: "",
            authorPhotoUrl = user.photoUrl?.toString() ?: "",
            content = content
        )
        docRef.set(post).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deletePost(postId: String): Result<Unit> = try {
        postsCollection.document(postId).delete().await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getPosts(): Flow<List<Post>> =
        postsCollection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .snapshots()
            .map { snapshot ->
                snapshot.documents.mapNotNull {
                    it.toObject(Post::class.java)
                }
            }
}