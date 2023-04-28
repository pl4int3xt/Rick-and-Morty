package com.example.rickandmorty.data.remote.repository

import com.example.rickandmorty.data.remote.api.RickAndMortyApi
import com.example.rickandmorty.data.remote.dto.CharacterDto
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
}