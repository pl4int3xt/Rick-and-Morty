package com.example.rickandmorty.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toLocationModel
import com.example.rickandmorty.data.remote.dto.LocationDto
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationsScreenViewModel @Inject constructor(
    pager: Pager<Int, LocationDto>
) : ViewModel(){
    val locations = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toLocationModel() }
        }
        .cachedIn(viewModelScope)


}