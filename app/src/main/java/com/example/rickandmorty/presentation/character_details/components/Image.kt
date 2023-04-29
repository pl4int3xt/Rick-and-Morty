package com.example.rickandmorty.presentation.character_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
            .clip(RoundedCornerShape(20.dp))
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(0.7f)
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
            ,
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