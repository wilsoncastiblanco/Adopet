package com.example.adopet.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.adopet.PetsUiState
import com.example.adopet.repository.color
import com.example.adopet.repository.icon

@Composable
fun Pets(modifier: Modifier, petsUiState: PetsUiState) {
    when (petsUiState) {
        is PetsUiState.Error -> Text("Error!")
        is PetsUiState.Loading -> CircularProgressIndicator()
        is PetsUiState.Success -> {
            Column {
                PetsTitle()
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

@Composable
fun PetsTitle() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "All Pets", fontSize = 22.sp)
        SeeAll(modifier)
    }
}


@Composable
fun SeeAll(modifier: Modifier) {
    Text(text = "See All", fontSize = 14.sp)
}

