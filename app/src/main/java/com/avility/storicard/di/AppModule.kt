package com.avility.storicard.di

import com.avility.data.repository.CardRepositoryImpl
import com.avility.data.repository.MovementRepositoryImpl
import com.avility.data.repository.UserRepositoryImpl
import com.avility.domain.repository.CardRepository
import com.avility.domain.repository.MovementRepository
import com.avility.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

   @Provides
   @Singleton
       fun provideFireAuth(): FirebaseAuth {
       return FirebaseAuth.getInstance()
   }

    @Provides
    @Singleton
    fun provideUserRepository(fireStore: FirebaseFirestore, fireAuth: FirebaseAuth): UserRepository {
        return UserRepositoryImpl(fireStore, fireAuth)
    }

    @Provides
    @Singleton
    fun providesCardRepository(fireStore: FirebaseFirestore): CardRepository {
        return CardRepositoryImpl(fireStore)
    }

    @Provides
    @Singleton
    fun providesMovementRepository(fireStore: FirebaseFirestore): MovementRepository {
        return MovementRepositoryImpl(fireStore)
    }
}