package com.example.rickandmorty.presentation.location_details

import com.example.rickandmorty.domain.model.LocationModel

data class LocationDetailsState(
    val isLoading: Boolean = false,
    val message: String = "",
    val locationModel: LocationModel? = null
)