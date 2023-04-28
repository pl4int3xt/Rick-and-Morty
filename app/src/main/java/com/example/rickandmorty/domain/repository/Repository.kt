package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.Result

interface Repository {

    suspend fun getAllCharacters(page: Int): Result
    suspend fun getCharacterDetails(id: Int): CharacterDto
}