package com.example.adopet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import okhttp3.Route

sealed class Routes(val icon: ImageVector, val route: String) {
    object Home: Routes(Icons.Filled.Home, "home")
    object Favorites: Routes(Icons.Filled.Favorite, "favorites")
    object Profile: Routes(Icons.Filled.AccountCircle, "profile")
    object Notifications: Routes(Icons.Filled.Notifications, "notifications")
}

sealed class InternalRoutes(val root: Routes, val route: String) {
    fun createRoute() = "${root.route}/$route"
    object ForgotPassword: InternalRoutes(Routes.Profile, "forgot-password")
    object PetDetail: InternalRoutes(Routes.Home, "pet-detail/{petId}") {
        fun createRoute(petId: String): String {
            return "${root.route}/pet-detail/$petId"
        }
    }
}

val routes = listOf(
    Routes.Home,
    Routes.Favorites,
    Routes.Profile,
    Routes.Notifications
)