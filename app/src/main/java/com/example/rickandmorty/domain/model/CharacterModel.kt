package com.example.rickandmorty.domain.model

import com.example.rickandmorty.data.remote.dto.Location
import com.example.rickandmorty.data.remote.dto.Origin

data class CharacterModel(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
