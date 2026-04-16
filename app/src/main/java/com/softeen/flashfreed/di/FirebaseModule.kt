package com.softeen.flashfreed.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.softeen.flashfreed.BuildConfig
import com.softeen.flashfreed.data.repository.AuthRepository
import com.softeen.flashfreed.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        val db = Firebase.firestore
        return db
    }

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth {
        val auth = Firebase.auth
        return auth
    }

    @Provides
    @Singleton
    fun provideStorage(): FirebaseStorage {
        val storage = Firebase.storage
        return storage
    }

}
