package com.example.adopet.repository

import com.example.adopet.model.PetCategory

interface CategoriesRepository {
    suspend fun getCategories(): List<PetCategory>
}