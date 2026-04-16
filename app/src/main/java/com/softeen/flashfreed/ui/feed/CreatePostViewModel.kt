package com.softeen.flashfreed.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeen.flashfreed.data.repository.AuthRepository
import com.softeen.flashfreed.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CreatePostState>(CreatePostState.Idle)
    val state: StateFlow<CreatePostState> = _state

    fun createPost(content: String) {
        val user = authRepository.currentUser.value
            ?: return
        viewModelScope.launch {
            _state.emit(CreatePostState.Loading)
            postRepository.createPost(content, user)
                .onSuccess { _state.emit(CreatePostState.Success) }
                .onFailure { _state.emit(CreatePostState.Error(it.message)) }
        }
    }
}

sealed class CreatePostState {
    object Idle : CreatePostState()
    object Loading : CreatePostState()
    object Success : CreatePostState()
    data class Error(val message: String?) : CreatePostState()
}