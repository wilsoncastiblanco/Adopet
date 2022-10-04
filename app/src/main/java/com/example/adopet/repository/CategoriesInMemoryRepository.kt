package com.example.adopet.repository

import com.example.adopet.model.PetCategory

class CategoriesInMemoryRepository: CategoriesRepository {

    override suspend fun getCategories(): List<PetCategory> {
        return petCategories
    }

}