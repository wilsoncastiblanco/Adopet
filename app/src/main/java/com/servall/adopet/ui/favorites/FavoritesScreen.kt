package com.servall.adopet.ui.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.servall.adopet.model.Pet
import com.servall.adopet.ui.pets.PetCard
import com.servall.adopet.ui.pets.Up

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    val uiState by favoritesViewModel.favoritesUiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is FavoritesUiState.Error -> Text(text = "Error loading favorites!")
        is FavoritesUiState.Loading -> CircularProgressIndicator()
        is FavoritesUiState.Success -> FavoritesList(state.pets, modifier, navigateUp)
    }
}

@Composable
fun FavoritesList(
    pets: List<Pet>, modifier: Modifier, navigateUp: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = { Up(upPress = navigateUp) },
            )
        },

        ) { padding ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(padding)) {
            items(pets) { pet ->
                PetCard(pet = pet) {

                }
            }
        }
    }
}


