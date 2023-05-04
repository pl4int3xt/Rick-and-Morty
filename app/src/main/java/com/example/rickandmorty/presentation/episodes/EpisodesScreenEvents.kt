package com.example.rickandmorty.presentation.episodes

sealed class EpisodesScreenEvents {
    data class OnEpisodeClicked(val id: String): EpisodesScreenEvents()
    object OnPopBackStack: EpisodesScreenEvents()
    object OnRefresh: EpisodesScreenEvents()
}