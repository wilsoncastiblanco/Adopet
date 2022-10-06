package com.example.adopet.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.adopet.NavigationItemsProvider

@Composable
fun AdoptBottomBarContent() {
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    AdoptBottomBarStateless(selectedIndex) { index ->
        selectedIndex = index
    }
}

@Composable
fun AdoptBottomBarStateless(selectedIndex: Int, onNavItemChange: (Int) -> Unit) {
    BottomNavigation(
        modifier = Modifier.clip(
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        ),
        elevation = 4.dp
    ) {
        NavigationItemsProvider.navigationItems()
            .forEachIndexed { index, adoptNavItem ->
                BottomNavigationItem(
                    selected = selectedIndex == index,
                    icon = {
                        Icon(
                            imageVector = adoptNavItem.icon,
                            contentDescription = adoptNavItem.contentDescription
                        )
                    },
                    onClick = { onNavItemChange(index) }
                )
            }
    }
}
