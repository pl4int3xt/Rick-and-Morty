package com.example.rickandmorty.presentation.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toEpisodeModel
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodesScreenViewModel @Inject constructor(
    private val pager: Pager<Int, EpisodeDto>
): ViewModel() {
    val episodes = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toEpisodeModel() }
        }
        .cachedIn(viewModelScope)
}