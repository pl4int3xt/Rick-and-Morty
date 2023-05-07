package com.example.rickandmorty.presentation.episode_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rickandmorty.domain.model.EpisodeModel
import com.example.rickandmorty.presentation.episode_details.EpisodeDetailsEvents
import com.example.rickandmorty.presentation.episode_details.EpisodeDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailsScreen(
    episodeDetails: EpisodeDetailsState,
    onEvent: (EpisodeDetailsEvents) -> Unit
) {
    Scaffold {
        Box(modifier = Modifier.padding(top = it.calculateTopPadding())){
            if (episodeDetails.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (episodeDetails.message.isNotEmpty()){
                IconButton(onClick = { onEvent(EpisodeDetailsEvents.OnRefresh) }) {
                    Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "refresh")
                }
            }

            if (episodeDetails.episodeModel != null){
                LazyColumn{
                    item {
                        Column {
                            EpisodeCard(
                                name = episodeDetails.episodeModel.name,
                                episode = episodeDetails.episodeModel.episode,
                                airDate = episodeDetails.episodeModel.airDate,
                                url = episodeDetails.episodeModel.url,
                                created = episodeDetails.episodeModel.created,
                            )
                            EpisodeCard(
                                isList = true,
                                list = episodeDetails.episodeModel.characters
                            )
                        }
                    }
                }
            }
        }
    }
}