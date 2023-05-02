package com.example.rickandmorty.data.remote.dto

data class EpisodeResult(
    val info: Info,
    val results: List<EpisodeDto>
)