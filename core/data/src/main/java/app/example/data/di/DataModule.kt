package app.example.data.di

import app.example.data.datastore.PreferencesManager
import app.example.data.datastore.TokenRepositoryImpl
import app.example.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTokenRepository(preferencesManager: PreferencesManager): TokenRepository {
        return TokenRepositoryImpl(preferencesManager)
    }
}