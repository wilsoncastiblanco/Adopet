package com.example.adopet.repository

import com.example.adopet.model.Pet
import com.example.adopet.model.PetType

interface PetsRepository {
    suspend fun getPets(): List<Pet>
    suspend fun filterByType(categoriesSelected: Set<PetType>): List<Pet>
}