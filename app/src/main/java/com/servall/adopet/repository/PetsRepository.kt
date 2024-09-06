package com.servall.adopet.repository

import com.servall.adopet.model.Pet
import com.servall.adopet.model.PetType

interface PetsRepository {
    suspend fun getPets(): List<Pet>
    suspend fun filterByType(categoriesSelected: Set<PetType>): List<Pet>
    suspend fun getById(petId: String): Pet?
    suspend fun search(query: String): List<Pet>
    suspend fun saveFavorite(petId: String): Boolean
    suspend fun getFavorites(): List<Pet>
}