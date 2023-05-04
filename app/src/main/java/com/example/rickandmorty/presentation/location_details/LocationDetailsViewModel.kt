package com.example.rickandmorty.presentation.location_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.domain.use_case.GetLocationsDetailsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LocationDetailsViewModel @Inject constructor(
    private val getLocationsDetailsUseCase: GetLocationsDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private var id by mutableStateOf(0)

    private val _state = mutableStateOf(LocationDetailsState())
    val state = _state
    init {
        id = savedStateHandle.get<String>("id")?.toInt() ?: 0
        getLocationDetails(id)
    }

    private fun getLocationDetails(id: Int){
        getLocationsDetailsUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = LocationDetailsState(message = result.message ?: "Unknown error")
                }
                is Resource.Loading -> {
                    _state.value = LocationDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = LocationDetailsState(locationModel = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}