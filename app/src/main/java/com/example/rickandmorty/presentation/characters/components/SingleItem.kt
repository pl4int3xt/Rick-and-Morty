package com.example.rickandmorty.presentation.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun SingleItem(
    item: ListItem,
    imageUrl: String,
    name: String
) {
    Box(
        modifier = Modifier.height(item.height)
            .background(color = item.color)
    ){
        AsyncImage(
            modifier = Modifier.height(item.height),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = name,
        )
    }
}