package com.servall.adopet.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.servall.adopet.model.Pet
import com.servall.adopet.repository.PetsInMemoryRepository
import com.servall.adopet.repository.PetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val petsRepository: PetsRepository = PetsInMemoryRepository
): ViewModel() {

    private val _favoritesUiState: MutableStateFlow<FavoritesUiState> =
        MutableStateFlow(FavoritesUiState.Loading)
    val favoritesUiState: StateFlow<FavoritesUiState>
        get() = _favoritesUiState

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            val favorites = petsRepository.getFavorites()
            _favoritesUiState.value = FavoritesUiState.Success(favorites)
        }
    }
}

sealed class FavoritesUiState {
    data class Success(val pets: List<Pet>) : FavoritesUiState()
    data class Error(val message: String) : FavoritesUiState()
    object Loading : FavoritesUiState()

}