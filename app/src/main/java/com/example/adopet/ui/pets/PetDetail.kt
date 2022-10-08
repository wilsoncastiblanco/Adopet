package com.example.adopet.ui.pets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PetDetail(
    navigateUp: () -> Unit,
    petId: String,
    petsDetailViewModel: PetDetailViewModel = viewModel(
        factory = PetDetailViewModelFactory(petId)
    )
) {
    val petDetailUiState by petsDetailViewModel.petDetailUiState.collectAsStateWithLifecycle()
    PetDetailContent(
        navigateUp = navigateUp,
        petDetailUiState = petDetailUiState
    )
}

@Composable
fun PetDetailContent(
    navigateUp: () -> Unit,
    petDetailUiState: PetDetailUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pet Detail") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back button from Pet Detail"
                        )
                    }
                },
                backgroundColor = Color.White
            )
        }

    ) { padding ->
        when (petDetailUiState) {
            is PetDetailUiState.Error -> Text("Error! ${petDetailUiState.message}")
            PetDetailUiState.Loading -> CircularProgressIndicator()
            is PetDetailUiState.Success -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = petDetailUiState.pet.name)
                }
            }
        }
    }
}