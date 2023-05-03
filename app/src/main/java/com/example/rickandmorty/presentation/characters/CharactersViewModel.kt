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
import com.example.rickandmorty.presentation.screens.Screens
import com.example.rickandmorty.presentation.uiEvents.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    pager: Pager<Int, CharacterDto>
): ViewModel() {

    val pagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCharacterModel() }
        }
        .cachedIn(viewModelScope)

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun onEvent(charactersScreenEvents: CharactersScreenEvents){
        when(charactersScreenEvents){
            is CharactersScreenEvents.OnRefreshClicked -> {
                getAllCharacters()
            }
            is CharactersScreenEvents.OnNavigateToCharacterDetails -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnNavigate(Screens.CharacterDetails.route + "/${charactersScreenEvents.id}"))
                }
            }
        }
    }
}