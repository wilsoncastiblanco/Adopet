package com.example.adopet.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
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
import com.example.adopet.PetsUiState
import com.example.adopet.PetsViewModel
import com.example.adopet.model.PetType
import com.example.adopet.ui.pets.Pets

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PetsContent(
    openPetDetail: (String) -> Unit,
    petsViewModel: PetsViewModel = viewModel()) {
    val categoriesUiState by petsViewModel.categoriesUiState.collectAsStateWithLifecycle()
    val petsUiState by petsViewModel.petsUiState.collectAsStateWithLifecycle()
    PetsStateless(
        petsUiState = petsUiState,
        categoriesUiState = categoriesUiState,
        onCategoryClick = petsViewModel::filterPets,
        openPetDetail = openPetDetail
    )
}


@Composable
fun PetsStateless(
    petsUiState: PetsUiState,
    categoriesUiState: CategoriesUiState,
    onCategoryClick: (PetType, Boolean) -> Unit,
    openPetDetail: (String) -> Unit,
) {

    Scaffold(
        topBar = { AdoptTopBar() }
    ) { padding ->
        val modifierWithPadding = Modifier.padding(padding)
        Column {
            SearchBar(modifierWithPadding)
            Greeting(modifierWithPadding, name = "Compose!")
            PetsCategories(modifierWithPadding, categoriesUiState, onCategoryClick)
            Pets(modifierWithPadding, petsUiState, openPetDetail)
        }
    }
}

@Composable
fun Greeting(modifier: Modifier, name: String) {
    Row() {
        Text(text = "Hello, ", fontSize = 24.sp)
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}
