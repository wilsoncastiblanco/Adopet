package com.example.adopet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adopet.model.Pet
import com.example.adopet.model.PetCategory
import com.example.adopet.model.PetType
import com.example.adopet.repository.CategoriesInMemoryRepository
import com.example.adopet.repository.CategoriesRepository
import com.example.adopet.repository.PetsInMemoryRepository
import com.example.adopet.repository.PetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetsViewModel(
    private val categoriesRepository: CategoriesRepository = CategoriesInMemoryRepository(),
    private val petsRepository: PetsRepository = PetsInMemoryRepository()
) : ViewModel() {

    private val _categoriesUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val categoriesUiState: StateFlow<CategoriesUiState>
        get() = _categoriesUiState

    private val _petsUiState = MutableStateFlow<PetsUiState>(PetsUiState.Loading)
    val petsUiState: StateFlow<PetsUiState>
        get() = _petsUiState

    private val categoriesSelected: MutableSet<PetType> = mutableSetOf()

    init {
        initCategories()
        initPets()
    }

    private fun initPets() {
        viewModelScope.launch {
            try {
                petsRepository.getPets().run {
                    _petsUiState.value = PetsUiState.Success(this)
                }
            } catch (exception: Exception) {
                _petsUiState.value = PetsUiState.Error(exception.message ?: "An unexpected error happened with the Pets!!")
            }
        }
    }

    private fun initCategories() {
        viewModelScope.launch {
            try {
                categoriesRepository.getCategories().run {
                    _categoriesUiState.value = CategoriesUiState.Success(this)
                }
            } catch (exception: Exception) {
                _categoriesUiState.value = CategoriesUiState.Error(exception.message ?: "An unexpected error happened with the Categories!!")
            }
        }
    }

    fun filterPets(petType: PetType, isSelected: Boolean) {
        viewModelScope.launch {
            try {
                if (isSelected) {
                    categoriesSelected.add(petType)
                } else {
                    categoriesSelected.remove(petType)
                }
                petsRepository.filterByType(categoriesSelected).run {
                    _petsUiState.value = PetsUiState.Success(this)
                }
            } catch (exception: Exception) {
                _petsUiState.value = PetsUiState.Error(exception.message ?: "An unexpected error happened filtering the Pets!!")
            }
        }
    }

}

sealed interface CategoriesUiState {
    data class Success(val categories: List<PetCategory>) : CategoriesUiState
    data class Error(val error: String) : CategoriesUiState
    object Loading : CategoriesUiState
}

sealed interface PetsUiState {
    data class Success(val pets: List<Pet>) : PetsUiState
    data class Error(val error: String) : PetsUiState
    object Loading : PetsUiState
}