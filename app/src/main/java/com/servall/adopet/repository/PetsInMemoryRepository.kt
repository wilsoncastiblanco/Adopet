package com.servall.adopet.repository

import com.servall.adopet.model.Pet
import com.servall.adopet.model.PetType

object PetsInMemoryRepository : PetsRepository {

    private val favorites = mutableSetOf<Pet>()

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

    override suspend fun saveFavorite(petId: String): Boolean {
        if (favorites.find { it.id == petId.toLong() } != null) {
            favorites.removeIf { it.id == petId.toLong() }
            return false
        } else {
            favorites.add(getById(petId)!!)
            return true
        }
    }

    override suspend fun getFavorites(): List<Pet> {
        return favorites.toList()
    }
}