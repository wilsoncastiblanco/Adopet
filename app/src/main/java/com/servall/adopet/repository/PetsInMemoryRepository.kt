package com.servall.adopet.repository

import com.servall.adopet.model.Pet
import com.servall.adopet.model.PetType

class PetsInMemoryRepository : PetsRepository {

    override suspend fun getPets(): List<Pet> {
        return petsForAdoption.map { it.pet }
    }

    override suspend fun filterByType(
        categoriesSelected: Set<PetType>
    ): List<Pet> {
        if (categoriesSelected.isEmpty()) return getPets()
        return getPets().filter { it.type in categoriesSelected }
    }

    override suspend fun getById(petId: String): Pet? {
        return getPets().find { it.id == petId.toLong() }
    }

    override suspend fun search(query: String): List<Pet> {
        return getPets().filter { it.name.lowercase().contains(query.lowercase()) }
    }
}