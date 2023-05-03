package com.example.rickandmorty.presentation.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toEpisodeModel
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import com.example.rickandmorty.presentation.screens.Screens
import com.example.rickandmorty.presentation.uiEvents.UiEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesScreenViewModel @Inject constructor(
    pager: Pager<Int, EpisodeDto>
): ViewModel() {
    val episodes = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toEpisodeModel() }
        }
        .cachedIn(viewModelScope)

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun onEvent(episodesScreenEvents: EpisodesScreenEvents){
        when(episodesScreenEvents){
            is EpisodesScreenEvents.OnEpisodeClicked -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnNavigate(Screens.EpisodeDetailsScreen.route +"/${episodesScreenEvents.id}"))
                }
            }
            EpisodesScreenEvents.OnPopBackStack -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnPopBackStack)
                }
            }
        }
    }
}