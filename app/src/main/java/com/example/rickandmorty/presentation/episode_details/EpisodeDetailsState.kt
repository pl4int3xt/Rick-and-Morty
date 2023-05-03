package com.example.rickandmorty.presentation.episode_details

import com.example.rickandmorty.domain.model.EpisodeModel

data class EpisodeDetailsState(
    val isLoading: Boolean = false,
    val message: String = "",
    val episodeModel: EpisodeModel? = null
)