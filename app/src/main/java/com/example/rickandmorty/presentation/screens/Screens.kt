package com.example.rickandmorty.presentation.screens

sealed class Screens(val route: String) {
    object CharactersScreen: Screens("characters_screen")
    object CharacterDetails: Screens("character_details_screen")
    object LocationScreen: Screens("characters_screen")
    object EpisodeScreen: Screens("episode_screen")
}