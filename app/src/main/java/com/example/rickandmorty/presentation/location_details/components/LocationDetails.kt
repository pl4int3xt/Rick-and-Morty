package com.example.rickandmorty.presentation.location_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.rickandmorty.presentation.location_details.LocationDetailsEvents
import com.example.rickandmorty.presentation.location_details.LocationDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailsScreen(
    onEvent: (LocationDetailsEvents) -> Unit,
    locationDetails: LocationDetailsState
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            if (locationDetails.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (locationDetails.message.isNotEmpty()){
                IconButton(onClick = { onEvent(LocationDetailsEvents.OnRefresh) }) {
                    Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "refresh")
                }
            }

            if (locationDetails.locationModel != null){
                LazyColumn{
                    item {
                        Spacer(modifier = Modifier.height(it.calculateTopPadding()))
                    }
                    item {
                        Column {
                            LocationCard(
                                created = locationDetails.locationModel.created ,
                                dimension = locationDetails.locationModel.dimension,
                                name = locationDetails.locationModel.name,
                                type = locationDetails.locationModel.type,
                                url = locationDetails.locationModel.url
                            )
                            LocationCard(
                                isList = true,
                                residents = locationDetails.locationModel.residents
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(it.calculateBottomPadding()))
                    }
                }
            }
        }
    }
}