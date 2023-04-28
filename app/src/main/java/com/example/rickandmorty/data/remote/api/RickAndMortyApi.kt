package com.example.rickandmorty.data.remote.api

import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Result

    @GET("character/{id}")
    suspend fun characterDetails(
        @Path("id") id: Int
    ): CharacterDto
}