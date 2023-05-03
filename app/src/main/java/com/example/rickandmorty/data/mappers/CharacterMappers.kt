package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import com.example.rickandmorty.domain.model.CharacterModel
import com.example.rickandmorty.domain.model.EpisodeModel

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

fun EpisodeDto.toEpisodeModel(): EpisodeModel{
    return EpisodeModel(
        airDate = air_date,
        characters = characters,
        created = created,
        episode = episode,
        id = id,
        name = name,
        url = url
    )
}