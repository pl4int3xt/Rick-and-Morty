package com.example.rickandmorty.presentation.screens

sealed class Screens(val route: String) {
    object CharactersScreen: Screens("characters_screen")
    object CharacterDetails: Screens("character_details_screen")
    object LocationScreen: Screens("location_screen")
    object LocationDetailsScreen: Screens("location_details_screen")
    object EpisodeScreen: Screens("episode_screen")
    object EpisodeDetailsScreen: Screens("episode_details_screen")
}