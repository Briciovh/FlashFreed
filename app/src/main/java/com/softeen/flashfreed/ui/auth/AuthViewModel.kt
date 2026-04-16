package com.softeen.flashfreed.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeen.flashfreed.data.repository.AuthRepository
import com.softeen.flashfreed.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val currentUser = authRepository.currentUser

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun signInWithGoogle(context: Context) {
        viewModelScope.launch {
            _authState.emit(AuthState.Loading)
            authRepository.signInWithGoogle(context)
                .onSuccess { user ->
                    userRepository.createOrUpdateProfile(user)
                    _authState.emit(AuthState.Success)
                }
                .onFailure {
                    _authState.emit(AuthState.Error(it.message))
                }
        }
    }

    fun signOut() = viewModelScope.launch { authRepository.signOut() }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String?) : AuthState()
}