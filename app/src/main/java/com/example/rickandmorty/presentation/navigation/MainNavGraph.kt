package com.example.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.presentation.character_details.CharacterDetailsViewModel
import com.example.rickandmorty.presentation.character_details.components.CharacterDetailsScreen
import com.example.rickandmorty.presentation.characters.CharactersViewModel
import com.example.rickandmorty.presentation.characters.components.CharactersScreen
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
            val characters = viewModel.getAllCharacters().collectAsLazyPagingItems()
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
    }
}