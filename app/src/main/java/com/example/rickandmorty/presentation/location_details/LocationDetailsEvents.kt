package com.example.rickandmorty.presentation.location_details

sealed class LocationDetailsEvents {
    object OnPopBackStack: LocationDetailsEvents()
    object OnRefresh: LocationDetailsEvents()
}