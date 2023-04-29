package com.example.rickandmorty.presentation.characters.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.rickandmorty.domain.model.CharacterModel
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(
    characters: LazyPagingItems<CharacterModel>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = characters.loadState){
        if (characters.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (characters.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()){
            if (characters.loadState.refresh is LoadState.Loading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    items(characters){ character ->
                        if (character!=null){
                            SingleItem(
                                item = ListItem(
                                    height = Random.nextInt(100, 300).dp,
                                    color = Color(
                                        Random.nextLong(0xFFFFFFFF)
                                    ).copy(alpha = 1f),
                                ),
                                imageUrl = character.image,
                                name = character.name)
                        }
                    }
                    item {
                        if (characters.loadState.append is LoadState.Loading){
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}