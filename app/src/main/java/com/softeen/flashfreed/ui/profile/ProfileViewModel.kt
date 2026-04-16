package com.softeen.flashfreed.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeen.flashfreed.data.model.UserProfile
import com.softeen.flashfreed.data.repository.AuthRepository
import com.softeen.flashfreed.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val profile: StateFlow<UserProfile?> =
        authRepository.currentUser
            .filterNotNull()
            .flatMapLatest { user ->
                userRepository.getProfile(user.uid)
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}