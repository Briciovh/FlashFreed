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

    private const val EMULATOR_HOST = "10.0.2.2"

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        val db = Firebase.firestore
        if (BuildConfig.DEBUG) {
            db.useEmulator(EMULATOR_HOST, 8080)
        }
        return db
    }

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth {
        val auth = Firebase.auth
        if (BuildConfig.DEBUG) {
            auth.useEmulator(EMULATOR_HOST, 9099)
        }
        return auth
    }

    @Provides
    @Singleton
    fun provideStorage(): FirebaseStorage {
        val storage = Firebase.storage
        if (BuildConfig.DEBUG) {
            storage.useEmulator(EMULATOR_HOST, 9199)
        }
        return storage
    }

}
