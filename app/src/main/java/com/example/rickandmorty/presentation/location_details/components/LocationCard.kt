package com.example.rickandmorty.presentation.location_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationCard(
    isList: Boolean = false,
    created: String = "",
    dimension: String = "",
    name: String = "",
    residents: List<String> = emptyList(),
    type: String = "",
    url: String = ""
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
                residents.forEach { value ->
                    Text(text = value)
                }
            }
            Text(text = name)
            Text(text = dimension)
            Text(text = type)
            Text(text = url)
            Text(text = created)
        }
    }
}