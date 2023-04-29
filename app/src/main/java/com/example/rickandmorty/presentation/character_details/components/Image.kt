package com.example.rickandmorty.presentation.character_details.components

import android.media.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Image(
    imageUrl: String,
    name: String,
    origin: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(0.7f),
            model = imageUrl,
            contentDescription = name
        )
        Text(
            text = name,
            fontSize = 25.sp
        )
        Text(
            text = origin,
            fontSize = 25.sp
        )
    }
}