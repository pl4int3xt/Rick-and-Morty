package com.example.rickandmorty.presentation.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    Scaffold {
        LazyColumn(){
            items(characters){ character ->
                AsyncImage(model = character?.image, contentDescription = character?.name)
            }
        }
    }
}