package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toCharacterModel
import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.domain.model.CharacterModel
import com.example.rickandmorty.domain.use_case.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
): ViewModel() {

    val characters = getAllCharactersUseCase().cachedIn(viewModelScope)
}