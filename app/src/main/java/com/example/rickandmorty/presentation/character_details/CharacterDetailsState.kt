package com.example.rickandmorty.presentation.character_details

import com.example.rickandmorty.domain.model.CharacterModel

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val characterDetails: CharacterModel? = null
)
