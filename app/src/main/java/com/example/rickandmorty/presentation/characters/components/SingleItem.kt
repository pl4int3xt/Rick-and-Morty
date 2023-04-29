package com.example.rickandmorty.presentation.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SingleItem(
    item: ListItem,
    imageUrl: String,
    name: String
) {
    Box(
        modifier = Modifier.height(item.height)
            .clip(RoundedCornerShape(16.dp))
            .background(color = item.color)
    ){
        AsyncImage(
            modifier = Modifier.height(item.height)
                .clip(RoundedCornerShape(16.dp))
            ,
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = name,
        )
    }
}