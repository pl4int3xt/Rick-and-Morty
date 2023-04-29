package com.example.rickandmorty.presentation.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.domain.use_case.GetCharacterDetailsUseCase
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    init {
        savedStateHandle
    }

    fun getCharacterDetails(id: Int){
        getCharacterDetailsUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        }
    }
}