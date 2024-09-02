package com.servall.adopet.repository

import com.servall.adopet.model.PetCategory

class CategoriesInMemoryRepository: CategoriesRepository {

    override suspend fun getCategories(): List<PetCategory> {
        return petCategories
    }

}