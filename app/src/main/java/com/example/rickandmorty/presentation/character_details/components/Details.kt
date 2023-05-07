package com.example.rickandmorty.presentation.character_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.domain.model.CharacterModel

@Composable
fun Details(
    characterModel: CharacterModel
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = characterModel.status)
            Text(text = characterModel.gender)
            Text(text = characterModel.species)
            Text(text = characterModel.type)
            Text(text = characterModel.location.name)
            Text(text = characterModel.location.url)
            Text(text = characterModel.url)
            characterModel.episode.forEach {
                Text(text = it)
            }
        }
    }
}