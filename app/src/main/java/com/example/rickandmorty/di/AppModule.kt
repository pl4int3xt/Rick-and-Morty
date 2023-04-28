package com.example.rickandmorty.di

import com.example.rickandmorty.common.Constants
import com.example.rickandmorty.data.remote.api.RickAndMortyApi
import com.example.rickandmorty.data.remote.repository.RepositoryImpl
import com.example.rickandmorty.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRickAndMortyApi(): RickAndMortyApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(rickAndMortyApi: RickAndMortyApi): Repository{
        return RepositoryImpl(rickAndMortyApi)
    }
}