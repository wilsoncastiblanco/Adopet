package com.example.adopet.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adopet.CategoriesUiState
import com.example.adopet.Greeting
import com.example.adopet.Pets
import com.example.adopet.PetsCategories
import com.example.adopet.PetsUiState
import com.example.adopet.PetsViewModel
import com.example.adopet.model.PetType

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AdoptContent(modifier: Modifier, petsViewModel: PetsViewModel = viewModel()) {
    val categoriesUiState by petsViewModel.categoriesUiState.collectAsStateWithLifecycle()
    val petsUiState by petsViewModel.petsUiState.collectAsStateWithLifecycle()
    AdoptContentStateless(
        modifier = modifier,
        petsUiState = petsUiState,
        categoriesUiState = categoriesUiState,
        onCategoryClick = petsViewModel::filterPets
    )
}

@Composable
fun AdoptContentStateless(
    modifier: Modifier,
    petsUiState: PetsUiState,
    categoriesUiState: CategoriesUiState,
    onCategoryClick: (PetType, Boolean) -> Unit
) {
    Column {
        Greeting(modifier, name = "Denis")
        PetsCategories(modifier, categoriesUiState, onCategoryClick)
        Pets(modifier, petsUiState)
    }
}


@Composable
fun Greeting(modifier: Modifier, name: String) {
    Row() {
        Text(text = "Hello, ", fontSize = 24.sp)
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}
