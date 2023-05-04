package com.example.rickandmorty.presentation.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SingleItem(
    item: ListItem,
    imageUrl: String,
    name: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = item.color)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomCenter
    ){
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = name,
        )
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.White)
                .clip(RoundedCornerShape(16.dp)),
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )
    }
}