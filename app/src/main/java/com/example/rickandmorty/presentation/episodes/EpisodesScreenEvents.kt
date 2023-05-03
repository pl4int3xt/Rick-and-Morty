package com.example.rickandmorty.presentation.episodes

sealed class EpisodesScreenEvents {
    data class OnEpisodeClicked(val id: Int): EpisodesScreenEvents()
    object OnPopBackStack: EpisodesScreenEvents()
}