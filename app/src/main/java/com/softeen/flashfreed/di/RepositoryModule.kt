package com.softeen.flashfreed.di

import com.softeen.flashfreed.data.repository.AuthRepository
import com.softeen.flashfreed.data.repository.AuthRepositoryImpl
import com.softeen.flashfreed.data.repository.PostRepository
import com.softeen.flashfreed.data.repository.PostRepositoryImpl
import com.softeen.flashfreed.data.repository.UserRepository
import com.softeen.flashfreed.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds @Singleton
    abstract fun bindPostRepository(
        impl: PostRepositoryImpl
    ): PostRepository

}