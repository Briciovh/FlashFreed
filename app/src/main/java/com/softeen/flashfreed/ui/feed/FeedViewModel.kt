package com.softeen.flashfreed.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeen.flashfreed.data.analytics.AnalyticsHelper
import com.softeen.flashfreed.data.model.Post
import com.softeen.flashfreed.data.repository.AuthRepository
import com.softeen.flashfreed.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val authRepository: AuthRepository,
    private val analyticsHelper: AnalyticsHelper
) : ViewModel() {

    val posts: StateFlow<List<Post>> =
        postRepository.getPosts()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    val currentUid: String?
        get() = authRepository.currentUser.value?.uid

    fun toggleLike(postId: String) {
        val uid = currentUid ?: return
        viewModelScope.launch {
            postRepository.toggleLike(postId, uid)
            analyticsHelper.logLikeToggled(true)
        }
    }

    fun isLikedByUser(postId: String): Flow<Boolean> {
        val uid = currentUid ?: return flowOf(false)
        return postRepository.isLikedByUser(postId, uid)
    }

    fun deletePost(postId: String) {
        viewModelScope.launch {
            postRepository.deletePost(postId)
        }
    }
}