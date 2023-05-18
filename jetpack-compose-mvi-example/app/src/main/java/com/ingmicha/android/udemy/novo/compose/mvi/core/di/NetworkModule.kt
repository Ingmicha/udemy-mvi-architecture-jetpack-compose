package com.ingmicha.android.udemy.novo.compose.mvi.core.di

import com.ingmicha.android.udemy.novo.compose.mvi.api.AnimalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/CatalinStefan/animalApi/master/"
    }

    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit): AnimalApi {
        return retrofit.create(AnimalApi::class.java)
    }

}