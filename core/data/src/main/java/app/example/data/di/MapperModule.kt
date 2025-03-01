package app.example.data.di

import app.example.data.mapper.LoginResponseMapper
import app.example.data.mapper.ProductMapper
import app.example.data.mapper.RatingMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideLoginResponseMapper(): LoginResponseMapper {
        return LoginResponseMapper()
    }

    @Provides
    @Singleton
    fun provideRatingMapper(): RatingMapper {
        return RatingMapper()
    }

    @Provides
    @Singleton
    fun provideProductMapper(ratingMapper: RatingMapper): ProductMapper {
        return ProductMapper(ratingMapper)
    }
}