package com.example.adopet.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.adopet.routes

@Composable
fun AdoptBottomNavigation(destination: NavDestination?, onSelectNavigation: (String) -> Unit) {
    BottomNavigation(
        modifier = Modifier.clip(
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        ),
        elevation = 4.dp
    ) {
        routes.forEach { screen ->
                BottomNavigationItem(
                    selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.route
                        )
                    },
                    onClick = { onSelectNavigation(screen.route) }
                )
            }
    }
}
