package com.servall.adopet.ui.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.servall.adopet.model.Pet
import com.servall.adopet.repository.PetsInMemoryRepository
import com.servall.adopet.repository.PetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetDetailViewModel(
    private val petId: String,
    private val petsRepository: PetsRepository = PetsInMemoryRepository
) : ViewModel() {

    private val _petDetailUiState: MutableStateFlow<PetDetailUiState> =
        MutableStateFlow(PetDetailUiState.Loading)
    val petDetailUiState: StateFlow<PetDetailUiState>
        get() = _petDetailUiState

    init {
        loadPetDetail()
    }

    private fun loadPetDetail() {
        viewModelScope.launch {
            try {
                val pet: Pet? = petsRepository.getById(petId)
                if (pet != null) {
                    _petDetailUiState.value = PetDetailUiState.Success(pet)
                } else {
                    _petDetailUiState.value =
                        PetDetailUiState.Error("Pet Detail can't be loaded, petId null")
                }
            } catch (exception: Exception) {
                _petDetailUiState.value = PetDetailUiState.Error(
                    exception.message ?: "There was an issue retrieving the Pet Detail"
                )
            }
        }
    }

    fun saveFavorite(petId: String) {
        viewModelScope.launch {
            try {
                val isFavorite = petsRepository.saveFavorite(petId)
                _petDetailUiState.value = PetDetailUiState.Success(petsRepository.getById(petId)!!, isFavorite = isFavorite)

            } catch (exception: Exception) {
                _petDetailUiState.value = PetDetailUiState.Error(
                    exception.message ?: "There was an issue saving the favorite"
                )
            }
        }

    }

}

sealed class PetDetailUiState {
    data class Success(val pet: Pet, val isFavorite: Boolean = false) : PetDetailUiState()
    data class Error(val message: String) : PetDetailUiState()
    object Loading : PetDetailUiState()
}