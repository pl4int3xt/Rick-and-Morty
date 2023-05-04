package com.example.rickandmorty.presentation.main.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Landscape
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.rickandmorty.presentation.navigation.MainNavGraph
import com.example.rickandmorty.presentation.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Screens.CharactersScreen.route,
                        icon = Icons.Rounded.Home
                    ),
                    BottomNavItem(
                        name = "Episodes",
                        route = Screens.EpisodeScreen.route,
                        icon = Icons.Rounded.Tv
                    ),
                    BottomNavItem(
                        name = "Locations",
                        route = Screens.LocationScreen.route,
                        icon = Icons.Rounded.Landscape
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                })
        }
    ) {
        MainNavGraph(navHostController = navController)
    }
}