package com.example.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.presentation.character_details.CharacterDetailsViewModel
import com.example.rickandmorty.presentation.character_details.components.CharacterDetailsScreen
import com.example.rickandmorty.presentation.characters.CharactersViewModel
import com.example.rickandmorty.presentation.characters.components.CharactersScreen
import com.example.rickandmorty.presentation.episode_details.EpisodeDetailsViewModel
import com.example.rickandmorty.presentation.episode_details.components.EpisodeDetailsScreen
import com.example.rickandmorty.presentation.episodes.EpisodesScreenViewModel
import com.example.rickandmorty.presentation.episodes.components.EpisodeScreen
import com.example.rickandmorty.presentation.location_details.LocationDetailsViewModel
import com.example.rickandmorty.presentation.location_details.components.LocationDetailsScreen
import com.example.rickandmorty.presentation.locations.LocationsScreenViewModel
import com.example.rickandmorty.presentation.locations.components.LocationScreen
import com.example.rickandmorty.presentation.screens.Screens

@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.CharactersScreen.route
    ){
        composable(Screens.CharactersScreen.route){
            val viewModel: CharactersViewModel = hiltViewModel()
            val uiEvents = viewModel.uiEvents
            val characters = viewModel.pagingFlow.collectAsLazyPagingItems()
            CharactersScreen(
                onNavigate = { navHostController.navigate(it.route)},
                uiEvents = uiEvents,
                onEvent = viewModel::onEvent,
                characters = characters
            )
        }

        composable(Screens.CharacterDetails.route + "/{id}"){
            val viewModel: CharacterDetailsViewModel = hiltViewModel()
            val state = viewModel.state.value
            CharacterDetailsScreen(
                onEvent = viewModel::onEvent,
                state = state)
        }
        composable(Screens.EpisodeScreen.route){
            val viewModel: EpisodesScreenViewModel = hiltViewModel()
            val uiEvents = viewModel.uiEvents
            val episodes = viewModel.episodes.collectAsLazyPagingItems()
            EpisodeScreen(
                onNavigate = { navHostController.navigate(it.route)},
                uiEvents = uiEvents,
                onEvent = viewModel::onEvent,
                episodes = episodes
            )
        }
        composable(Screens.LocationScreen.route){
            val viewModel: LocationsScreenViewModel = hiltViewModel()
            val uiEvents = viewModel.uiEvents
            val locations = viewModel.locations.collectAsLazyPagingItems()
            LocationScreen(
                onNavigate = { navHostController.navigate(it.route)},
                uiEvents = uiEvents,
                onEvent = viewModel::onEvent,
                locations = locations
            )
        }
        composable(Screens.LocationDetailsScreen.route + "/{id}"){
            val viewModel: LocationDetailsViewModel = hiltViewModel()
            val locationDetails = viewModel.state.value
            LocationDetailsScreen(
                onEvent = viewModel::onEvent,
                locationDetails = locationDetails
            )
        }
        composable(Screens.EpisodeDetailsScreen.route + "/{id}"){
            val viewModel: EpisodeDetailsViewModel = hiltViewModel()
            val episodeDetails = viewModel.state.value
            EpisodeDetailsScreen(
                onEvent = viewModel::onEvent,
                episodeDetails = episodeDetails
            )
        }
    }
}