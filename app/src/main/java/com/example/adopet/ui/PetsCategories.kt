package com.example.adopet.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.adopet.CategoriesUiState
import com.example.adopet.model.PetCategory
import com.example.adopet.model.PetType

@Composable
fun PetsCategories(
    modifier: Modifier,
    categoriesUiState: CategoriesUiState,
    onCategoryClick: (PetType, Boolean) -> Unit
) {
    when (categoriesUiState) {
        is CategoriesUiState.Error -> Text("Error!")
        is CategoriesUiState.Loading -> CircularProgressIndicator()
        is CategoriesUiState.Success -> {
            LazyRow() {
                items(categoriesUiState.categories) { category ->
                    PetCategoryContent(category, onCategoryClick)
                }
            }
        }
    }

}

@Composable
fun PetCategoryContent(petCategory: PetCategory, onCategoryClick: (PetType, Boolean) -> Unit) {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    PetCategoryStateless(petCategory, isSelected) {
        isSelected = it
        onCategoryClick(petCategory.type, it)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetCategoryStateless(
    petCategory: PetCategory,
    isSelected: Boolean,
    onSelectCategory: (Boolean) -> Unit
) {
    FilterChip(
        shape = CircleShape,
        modifier = Modifier
            .size(72.dp)
            .padding(8.dp)
            .shadow(
                elevation = 5.dp,
                shape = CircleShape
            ),
        onClick = { onSelectCategory(!isSelected) },
        selected = isSelected,
        colors = ChipDefaults.outlinedFilterChipColors(
            selectedBackgroundColor = Color.LightGray,
            backgroundColor = Color.White,
            selectedContentColor = Color.White
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = petCategory.icon),
            contentDescription = "Category",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(32.dp)
                .padding(2.dp)
        )
    }
}
