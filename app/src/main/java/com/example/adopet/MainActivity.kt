package com.example.adopet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SelectableChipColors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adopet.model.Pet
import com.example.adopet.model.PetCategory
import com.example.adopet.repository.petCategories
import com.example.adopet.ui.theme.AdopetTheme
import kotlinx.coroutines.selects.select
import kotlin.random.Random

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdopetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { AdoptApp() }
            }
        }
    }
}

//Side Effect

@Composable
fun AdoptApp() {
    Scaffold(
        topBar = { AdoptTopBar() },
        bottomBar = { AdoptBottomBarContent() },
        content = { padding ->
            AdoptContent(Modifier.padding(10.dp))
        }
    )
}

@Composable
fun AdoptContent(modifier: Modifier) {
    Column {
        Greeting(name = "Denis")
        SeeAll()
        PetsCategories()
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetsCategories() {
    LazyRow(modifier = Modifier) {
        items(petCategories) { category ->
            PetCategoryContent(category)
        }
    }
}

@Composable
fun PetCategoryContent(petCategory: PetCategory) {
    var isSelected by rememberSaveable {  mutableStateOf(false)  }
    PetCategoryStateless(petCategory, isSelected) {
        isSelected = it
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetCategoryStateless(petCategory: PetCategory, isSelected: Boolean, onSelectCategory: (Boolean) -> Unit) {
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

//State Hoisting
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

@Composable
fun AdoptTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Main Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
        },
        modifier = Modifier,
        title = { Text(text = "") }
    )
}

@Composable
fun Greeting(name: String) {
    Row {
        Text(text = "Hello, ")
        Text(text = name, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SeeAll() {
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = "See All")
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AdopetTheme {
        AdoptApp()
    }
}