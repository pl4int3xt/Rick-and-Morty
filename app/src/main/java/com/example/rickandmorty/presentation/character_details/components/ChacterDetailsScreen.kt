package com.example.rickandmorty.presentation.character_details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.domain.model.CharacterModel
import com.example.rickandmorty.presentation.character_details.CharacterDetailsState
import com.example.rickandmorty.presentation.character_details.CharacterDetailsViewModel
import com.example.rickandmorty.presentation.characters.CharactersScreenEvents
import com.example.rickandmorty.presentation.characters.CharactersViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    onEvent: (CharactersScreenEvents) -> Unit,
    state: MutableState<CharacterDetailsState>
) {
    Scaffold {
        Box {
            if (state.value.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                return@Box
            }

            if (state.value.error.isNotEmpty()){
                Text(text = state.value.error)
                return@Box
            }

            state.value.characterDetails?.let {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(imageUrl = it.image, name = it.name, origin = it.origin.name)
                    Details(characterModel = it)
                }
            }
        }
    }
}