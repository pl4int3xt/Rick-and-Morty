package com.example.rickandmorty.presentation.episodes.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.rickandmorty.domain.model.EpisodeModel
import com.example.rickandmorty.presentation.characters.components.ListItem
import com.example.rickandmorty.presentation.characters.components.SingleItem
import com.example.rickandmorty.presentation.episodes.EpisodesScreenEvents
import com.example.rickandmorty.presentation.uiEvents.UiEvents
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeScreen(
    onNavigate: (UiEvents.OnNavigate) -> Unit,
    uiEvents: SharedFlow<UiEvents>,
    onEvent: (EpisodesScreenEvents) -> Unit,
    episodes: LazyPagingItems<EpisodeModel>,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        uiEvents.collectLatest { event ->
            when(event){
                is UiEvents.OnNavigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }
    LaunchedEffect(key1 = episodes.loadState){
        if (episodes.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (episodes.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()){
            when (episodes.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is LoadState.Error -> {
                    IconButton(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = { onEvent(EpisodesScreenEvents.OnRefresh) }) {
                        Icon(imageVector = Icons.Rounded.Refresh,
                            contentDescription = "refresh")
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items(episodes){ episode ->
                            if (episode!=null){
                                SingleItem(
                                    onClick = { onEvent(EpisodesScreenEvents.OnEpisodeClicked(episode.id.toString()))},
                                    item = ListItem(
                                        color = Color(
                                            Random.nextLong(0xFFFFFFFF)
                                        ),
                                    ),
                                    name = episode.name)
                            }
                        }
                        item {
                            if (episodes.loadState.append is LoadState.Loading){
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.BottomCenter)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}