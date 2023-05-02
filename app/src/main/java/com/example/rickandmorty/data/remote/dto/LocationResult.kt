package com.example.rickandmorty.data.remote.dto

data class LocationResult(
    val info: Info,
    val results: List<LocationDto>
)