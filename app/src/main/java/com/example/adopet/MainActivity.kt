package com.example.adopet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.adopet.model.PetCategory
import com.example.adopet.model.PetType
import com.example.adopet.repository.color
import com.example.adopet.repository.icon
import com.example.adopet.ui.theme.AdopetTheme

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
            AdoptContent(Modifier.padding(padding))
        }
    )
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AdoptContent(modifier: Modifier, petsViewModel: PetsViewModel = viewModel()) {
    val categoriesUiState by petsViewModel.categoriesUiState.collectAsStateWithLifecycle()
    val petsUiState by petsViewModel.petsUiState.collectAsStateWithLifecycle()
    AdoptContentStateless(
        modifier = modifier,
        petsUiState = petsUiState,
        categoriesUiState = categoriesUiState,
        onCategoryClick = petsViewModel::filterPets
    )
}

@Composable
fun AdoptContentStateless(
    modifier: Modifier,
    petsUiState: PetsUiState,
    categoriesUiState: CategoriesUiState,
    onCategoryClick: (PetType, Boolean) -> Unit
) {
    Column {
        Greeting(modifier, name = "Denis")
        PetsCategories(modifier, categoriesUiState, onCategoryClick)
        Pets(modifier, petsUiState)
    }
}

@Composable
fun Pets(modifier: Modifier, petsUiState: PetsUiState) {
    when(petsUiState) {
        is PetsUiState.Error -> Text("Error!")
        is PetsUiState.Loading -> CircularProgressIndicator()
        is PetsUiState.Success -> {

            Column() {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "All Pets", fontSize = 22.sp)
                    SeeAll(modifier)
                }
                LazyVerticalGrid(
                    modifier = modifier,
                    columns = GridCells.Fixed(2),
                    content = {
                        items(petsUiState.pets) { pet ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .height(250.dp),
                                elevation = 4.dp,
                                shape = RoundedCornerShape(18.dp)
                            ) {
                                Column {
                                    AsyncImage(
                                        model = pet.images.first(),
                                        contentDescription = pet.name,
                                        modifier = Modifier
                                            .size(190.dp)
                                            .clip(
                                                RoundedCornerShape(
                                                    bottomStart = 28.dp,
                                                    bottomEnd = 28.dp
                                                )
                                            ),
                                        contentScale = ContentScale.Crop,
                                    )
                                    Column(
                                        modifier = Modifier.padding(
                                            start = 24.dp,
                                            top = 8.dp,
                                            bottom = 8.dp,
                                            end = 16.dp
                                        )
                                    ) {
                                        Row {
                                            Text(text = pet.name, fontWeight = FontWeight.Bold)
                                            Icon(
                                                imageVector = pet.gender.icon(),
                                                contentDescription = pet.gender.name,
                                                tint = pet.gender.color()
                                            )
                                        }
                                        Text(text = "${pet.age} year", fontSize = 12.sp)
                                    }
                                }

                            }
                        }
                    }
                )
            }
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
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
fun Greeting(modifier: Modifier, name: String) {
    Row() {
        Text(text = "Hello, ", fontSize = 24.sp)
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}

@Composable
fun SeeAll(modifier: Modifier) {
    Text(text = "See All", fontSize = 14.sp)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AdopetTheme {
        AdoptApp()
    }
}