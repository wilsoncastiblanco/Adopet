package com.servall.adopet.ui

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
import com.servall.adopet.CategoriesUiState
import com.servall.adopet.PetsUiState
import com.servall.adopet.PetsViewModel
import com.servall.adopet.model.PetType
import com.servall.adopet.ui.pets.Pets

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PetsContent(
    modifier: Modifier,
    openPetDetail: (String) -> Unit,
    petsViewModel: PetsViewModel = viewModel()
) {
    val categoriesUiState by petsViewModel.categoriesUiState.collectAsStateWithLifecycle()
    val petsUiState by petsViewModel.petsUiState.collectAsStateWithLifecycle()
    PetsStateless(
        modifier = modifier,
        petsUiState = petsUiState,
        categoriesUiState = categoriesUiState,
        onCategoryClick = petsViewModel::filterPets,
        openPetDetail = openPetDetail,
        onSearchPet = petsViewModel::searchPets
    )
}


@Composable
fun PetsStateless(
    modifier: Modifier,
    petsUiState: PetsUiState,
    categoriesUiState: CategoriesUiState,
    onCategoryClick: (PetType, Boolean) -> Unit,
    openPetDetail: (String) -> Unit,
    onSearchPet: (String) -> Unit,
) {
    Column {
        SearchBar(modifier, onSearchPet = onSearchPet)
        Greeting(modifier, name = "Compose!")
        PetsCategories(modifier, categoriesUiState, onCategoryClick)
        Pets(modifier, petsUiState, openPetDetail)
    }
}

@Composable
fun Greeting(modifier: Modifier, name: String) {
    Row() {
        Text(text = "Hello, ", fontSize = 24.sp)
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}
