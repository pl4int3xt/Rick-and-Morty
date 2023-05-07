package com.example.rickandmorty.presentation.location_details.components

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
import com.example.rickandmorty.presentation.location_details.LocationDetailsEvents
import com.example.rickandmorty.presentation.location_details.LocationDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailsScreen(
    onEvent: (LocationDetailsEvents) -> Unit,
    locationDetails: State<LocationDetailsState>
) {
    Scaffold {
        Box(modifier = Modifier.padding(top = it.calculateTopPadding())){
            if (locationDetails.value.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (locationDetails.value.message.isNotEmpty()){
                IconButton(onClick = { onEvent(LocationDetailsEvents.OnRefresh) }) {
                    Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "refresh")
                }
            }

            if (locationDetails.value.locationModel != null){
                LazyColumn{
                    item {
                        Column {
                            LocationCard(
                                created = locationDetails.value.locationModel?.created?:"",
                                dimension = locationDetails.value.locationModel?.dimension?:"",
                                name = locationDetails.value.locationModel?.name?:"",
                                type = locationDetails.value.locationModel?.type?:"",
                                url = locationDetails.value.locationModel?.url?:""
                            )
                            LocationCard(
                                isList = true,
                                residents = locationDetails.value.locationModel?.residents?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }
}