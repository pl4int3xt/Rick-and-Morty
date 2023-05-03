package com.example.rickandmorty.presentation.episode_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.domain.use_case.GetEpisodeDetailsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val getEpisodeDetailsUseCase: GetEpisodeDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(EpisodeDetailsState())
    val state: State<EpisodeDetailsState> = _state
    init {
        savedStateHandle.get<String>("id")?.let {
            getEpisodeDetailsViewModel(it.toInt())
        }
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
}