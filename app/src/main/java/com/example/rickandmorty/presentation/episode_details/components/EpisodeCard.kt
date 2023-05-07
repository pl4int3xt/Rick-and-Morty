package com.example.rickandmorty.presentation.episode_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EpisodeCard(
    list: List<String> = emptyList(),
    isList: Boolean = false,
    name: String = "",
    episode: String = "",
    airDate: String = "",
    url: String = "",
    created: String = ""
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (isList){
                list.forEach { value ->
                    Text(text = value)
                }
            }
            Text(text = name)
            Text(text = episode)
            Text(text = airDate)
            Text(text = url)
            Text(text = created)
        }
    }
}