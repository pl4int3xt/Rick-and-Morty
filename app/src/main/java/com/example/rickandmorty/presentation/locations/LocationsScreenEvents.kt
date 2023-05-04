package com.example.rickandmorty.presentation.locations

sealed class LocationsScreenEvents {
    data class OnLocationClicked(val id: Int): LocationsScreenEvents()
}