package com.softeen.flashfreed.data.repository

import com.google.firebase.auth.FirebaseUser
import com.softeen.flashfreed.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun createPost(content: String, user: FirebaseUser): Result<Unit>
    suspend fun deletePost(postId: String): Result<Unit>
    fun getPosts(): Flow<List<Post>>
    suspend fun toggleLike(postId: String, uid: String): Result<Unit>
    fun isLikedByUser(postId: String, uid: String): Flow<Boolean>
}