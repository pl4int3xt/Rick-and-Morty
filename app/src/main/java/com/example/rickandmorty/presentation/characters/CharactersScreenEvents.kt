package com.example.rickandmorty.presentation.characters

sealed class CharactersScreenEvents {
    object OnRefreshClicked: CharactersScreenEvents()
    data class OnNavigateToCharacterDetails(val id: String): CharactersScreenEvents()
}