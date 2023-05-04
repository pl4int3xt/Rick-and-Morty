package com.example.rickandmorty.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toLocationModel
import com.example.rickandmorty.data.remote.dto.LocationDto
import com.example.rickandmorty.presentation.screens.Screens
import com.example.rickandmorty.presentation.uiEvents.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsScreenViewModel @Inject constructor(
    pager: Pager<Int, LocationDto>
) : ViewModel(){
    val locations = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toLocationModel() }
        }
        .cachedIn(viewModelScope)

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()
    fun onEvent(locationsScreenEvents: LocationsScreenEvents){
        when(locationsScreenEvents){
            is LocationsScreenEvents.OnLocationClicked -> {
                viewModelScope.launch {
                    _uiEvents.emit(
                        UiEvents.OnNavigate(
                            Screens.LocationDetailsScreen.route
                                    + "/${locationsScreenEvents.id}"))
                }
            }

            LocationsScreenEvents.OnRefresh -> {

            }
        }
    }
}