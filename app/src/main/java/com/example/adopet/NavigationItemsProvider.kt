package com.example.adopet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

object NavigationItemsProvider {


    fun navigationItems(): List<AdoptNavItem> {
        return listOf(
            AdoptNavItem(
                icon = Icons.Filled.Home,
                contentDescription = "Home Navigation"
            ),
            AdoptNavItem(
                icon = Icons.Filled.Favorite,
                contentDescription = "Favorite Pets Navigation"
            ),
            AdoptNavItem(
                icon = Icons.Filled.AccountCircle,
                contentDescription = "Profile Navigation"
            ),
            AdoptNavItem(
                icon = Icons.Filled.Notifications,
                contentDescription = "Home Navigation"
            )
        )
    }
}

data class AdoptNavItem(
    val icon: ImageVector,
    val contentDescription: String
)