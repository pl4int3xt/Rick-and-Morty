package com.example.rickandmorty.presentation.character_details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rickandmorty.presentation.character_details.CharacterDetailsScreenEvents
import com.example.rickandmorty.presentation.character_details.CharacterDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    onEvent: (CharacterDetailsScreenEvents) -> Unit,
    state: CharacterDetailsState
) {
    Scaffold { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (state.error.isNotEmpty()){
                Text(text = state.error)
            }

            state.characterDetails?.let {
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    item {
                        Image(imageUrl = it.image, name = it.name, origin = it.origin.name)
                        Details(characterModel = it)
                    }
                    item {
                        Spacer(modifier = Modifier.height(padding.calculateBottomPadding()))
                    }
                }
            }
        }
    }
}