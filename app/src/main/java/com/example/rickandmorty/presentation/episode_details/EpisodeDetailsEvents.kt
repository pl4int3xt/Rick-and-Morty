package com.example.rickandmorty.presentation.episode_details

sealed class EpisodeDetailsEvents {
    object OnPopBackStack: EpisodeDetailsEvents()
    object OnRefresh: EpisodeDetailsEvents()
}