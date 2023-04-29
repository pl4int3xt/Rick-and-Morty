package com.example.rickandmorty.presentation.character_details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rickandmorty.presentation.character_details.CharacterDetailsScreenEvents
import com.example.rickandmorty.presentation.character_details.CharacterDetailsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    onEvent: (CharacterDetailsScreenEvents) -> Unit,
    state: CharacterDetailsState
) {
    Scaffold {
        Box {
            if (state.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                return@Box
            }

            if (state.error.isNotEmpty()){
                Text(text = state.error)
                return@Box
            }

            state.characterDetails?.let {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(imageUrl = it.image, name = it.name, origin = it.origin.name)
                    Details(characterModel = it)
                }
            }
        }
    }
}