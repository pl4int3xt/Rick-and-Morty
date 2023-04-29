package com.example.rickandmorty.presentation.character_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.domain.use_case.GetCharacterDetailsUseCase
import com.example.rickandmorty.presentation.uiEvents.UiEvents
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    private val _state = mutableStateOf(CharacterDetailsState())
    val state: State<CharacterDetailsState> = _state
    init {
        savedStateHandle.get<String>("id")?.let { id ->
            getCharacterDetails(id.toInt())
        }
    }

    private fun getCharacterDetails(id: Int){
        getCharacterDetailsUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = CharacterDetailsState(error = result.message?:"Unknown error")
                    _uiEvents.emit(UiEvents.OnShowToast(result.message?:"unknown message"))
                }
                is Resource.Loading -> {
                    _state.value = CharacterDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CharacterDetailsState(characterDetails = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(characterDetailsScreenEvents: CharacterDetailsScreenEvents){
        when(characterDetailsScreenEvents){
            CharacterDetailsScreenEvents.OnPopBackStack -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnPopBackStack)
                }
            }
        }
    }
}