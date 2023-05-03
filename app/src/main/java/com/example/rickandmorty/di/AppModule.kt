package com.example.rickandmorty.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.common.Constants
import com.example.rickandmorty.data.data_sources.GetAllCharactersDataSource
import com.example.rickandmorty.data.remote.api.RickAndMortyApi
import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import com.example.rickandmorty.data.remote.dto.LocationDto
import com.example.rickandmorty.data.remote.repository.RepositoryImpl
import com.example.rickandmorty.domain.model.EpisodeModel
import com.example.rickandmorty.domain.repository.Repository
import com.example.rickandmorty.domain.use_case.GetAllCharactersUseCase
import com.example.rickandmorty.domain.use_case.GetAllEpisodesUseCase
import com.example.rickandmorty.domain.use_case.GetAllLocationsUseCase
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

    @Singleton
    @Provides
    fun provideCharactersPager(repository: Repository): Pager<Int, CharacterDto>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetAllCharactersUseCase(repository)
            }
        )
    }
    @Singleton
    @Provides
    fun provideEpisodesPager(repository: Repository): Pager<Int, EpisodeDto>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetAllEpisodesUseCase(repository)
            }
        )
    }
    @Singleton
    @Provides
    fun provideLocationsPager(repository: Repository): Pager<Int, LocationDto>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetAllLocationsUseCase(repository)
            }
        )
    }
}