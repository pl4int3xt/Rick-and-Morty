package com.example.rickandmorty.data.remote.repository

import com.example.rickandmorty.data.remote.api.RickAndMortyApi
import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import com.example.rickandmorty.data.remote.dto.EpisodeResult
import com.example.rickandmorty.data.remote.dto.LocationDto
import com.example.rickandmorty.data.remote.dto.LocationResult
import com.example.rickandmorty.data.remote.dto.Result
import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
): Repository {
    override suspend fun getAllCharacters(page: Int): Result {
        return rickAndMortyApi.getAllCharacters(page)
    }

    override suspend fun getCharacterDetails(id: Int): CharacterDto {
        return rickAndMortyApi.getCharacterDetails(id)
    }

    override suspend fun getAllEpisodes(page: Int): EpisodeResult {
        return rickAndMortyApi.getAllEpisodes(page)
    }

    override suspend fun getEpisodeDetails(id: Int): EpisodeDto {
        return rickAndMortyApi.getEpisodeDetails(id)
    }

    override suspend fun getAllLocations(page: Int): LocationResult {
        return rickAndMortyApi.getAllLocations(page)
    }

    override suspend fun getLocationDetails(id: Int): LocationDto {
        return rickAndMortyApi.getLocationDetails(id)
    }
}