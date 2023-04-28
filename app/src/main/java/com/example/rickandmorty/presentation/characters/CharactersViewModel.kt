package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toCharacterModel
import com.example.rickandmorty.data.remote.dto.CharacterDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val pager: Pager<Int, CharacterDto>
): ViewModel() {

    val characters = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCharacterModel() }
        }
        .cachedIn(viewModelScope)
}