package com.example.adopet.ui.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PetDetailViewModelFactory(private val petId: String): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetDetailViewModel(petId) as T
    }
}