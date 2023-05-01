package com.example.rickandmorty.presentation.uiEvents

sealed class UiEvents{
    object OnPopBackStack: UiEvents()
    data class OnNavigate(val route: String): UiEvents()
    data class OnShowToast(val message: String): UiEvents()
}
