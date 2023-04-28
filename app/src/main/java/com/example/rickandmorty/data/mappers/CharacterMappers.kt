package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.domain.model.CharacterModel

fun CharacterDto.toCharacterModel(): CharacterModel{
    return CharacterModel(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location,
        name = name,
        origin = origin,
        species = species,
        status = status,
        type = type,
        url = url
    )
}