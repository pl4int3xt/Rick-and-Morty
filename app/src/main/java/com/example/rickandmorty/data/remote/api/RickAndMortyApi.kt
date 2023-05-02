package com.example.rickandmorty.data.remote.api

import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Result

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): CharacterDto

    @GET("episode")
    suspend fun getAllEpisodes(
        @Query("page") page: Int
    ): Result

    @GET("episode/{id}")
    suspend fun getEpisodeDetails(
        @Path("id") id: Int
    ): Result

    @GET("location")
    suspend fun getAllLocations(
        @Query("page") page: Int
    ): Result

    @GET("location/{id}")
    suspend fun getLocationDetails(
        @Path("id") id: Int
    ): Result
}