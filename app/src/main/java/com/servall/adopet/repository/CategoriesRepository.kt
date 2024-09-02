package com.servall.adopet.repository

import com.servall.adopet.model.PetCategory

interface CategoriesRepository {
    suspend fun getCategories(): List<PetCategory>
}