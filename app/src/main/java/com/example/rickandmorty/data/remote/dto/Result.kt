package com.example.rickandmorty.data.remote.dto

data class Result(
    val info: Info,
    val results: List<CharacterDto>
)