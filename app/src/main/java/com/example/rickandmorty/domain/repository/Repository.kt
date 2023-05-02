package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import com.example.rickandmorty.data.remote.dto.EpisodeResult
import com.example.rickandmorty.data.remote.dto.LocationDto
import com.example.rickandmorty.data.remote.dto.LocationResult
import com.example.rickandmorty.data.remote.dto.Result

interface Repository {

    suspend fun getAllCharacters(page: Int): Result
    suspend fun getCharacterDetails(id: Int): CharacterDto
    suspend fun getAllEpisodes(page: Int): EpisodeResult
    suspend fun getEpisodeDetails(id: Int): EpisodeDto
    suspend fun getAllLocations(page: Int): LocationResult
    suspend fun getLocationDetails(id: Int): LocationDto
}