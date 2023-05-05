package com.example.rickandmorty.presentation.episode_details

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.domain.use_case.GetEpisodeDetailsUseCase
import com.example.rickandmorty.presentation.uiEvents.UiEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val getEpisodeDetailsUseCase: GetEpisodeDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private var id by mutableStateOf(0)

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    private val _state = mutableStateOf(EpisodeDetailsState())
    val state: State<EpisodeDetailsState> = _state
    init {
        id = savedStateHandle.get<String>("id")?.toInt() ?: 0
        getEpisodeDetailsViewModel(id)
    }

    private fun getEpisodeDetailsViewModel(id: Int){
        getEpisodeDetailsUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = EpisodeDetailsState(message = result.message?:"")
                }
                is Resource.Loading -> {
                    _state.value = EpisodeDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = EpisodeDetailsState(episodeModel = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(episodeDetailsEvents: EpisodeDetailsEvents){
        when(episodeDetailsEvents){
            is EpisodeDetailsEvents.OnPopBackStack -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnPopBackStack)
                }
            }
            is EpisodeDetailsEvents.OnRefresh -> {
                getEpisodeDetailsViewModel(id)
            }
        }
    }
}