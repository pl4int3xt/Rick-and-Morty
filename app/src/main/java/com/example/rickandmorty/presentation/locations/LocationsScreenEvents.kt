package com.example.rickandmorty.presentation.locations

sealed class LocationsScreenEvents {
    data class OnLocationClicked(val id: String): LocationsScreenEvents()
    object OnRefresh: LocationsScreenEvents()
}