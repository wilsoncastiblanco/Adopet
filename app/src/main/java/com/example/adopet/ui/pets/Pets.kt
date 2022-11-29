package com.example.adopet.ui.pets

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.adopet.PetsUiState
import com.example.adopet.R
import com.example.adopet.model.Gender
import com.example.adopet.model.Pet
import com.example.adopet.model.PetType
import com.example.adopet.model.Size
import com.example.adopet.repository.color
import com.example.adopet.repository.dogRandomImages
import com.example.adopet.repository.icon
import com.example.adopet.ui.theme.AdopetTheme

@Composable
fun Pets(
    modifier: Modifier,
    petsUiState: PetsUiState,
    openPetDetail: (String) -> Unit
) {
    when (petsUiState) {
        is PetsUiState.Error -> Text("Error!")
        is PetsUiState.Loading -> CircularProgressIndicator()
        is PetsUiState.Success -> {
            Column {
                PetsTitle()
                PetsCollection(modifier, petsUiState = petsUiState, openPetDetail = openPetDetail)
            }
        }
    }
}

@Composable
fun PetsCollection(
    modifier: Modifier,
    petsUiState: PetsUiState.Success,
    openPetDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        content = {
            items(petsUiState.pets, key = { pet -> pet.id }) { pet ->
                PetCard(pet, openPetDetail)
            }
        }
    )
}

@Composable
fun PetCard(pet: Pet, openPetDetail: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(250.dp)
            .clickable { openPetDetail(pet.id.toString()) },
        elevation = 4.dp,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column {
            PetImage(pet = pet)
            PetDescription(pet = pet)
        }
    }
}

@Composable
fun PetImage(pet: Pet) {
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
        placeholder = painterResource(id = R.drawable.ic_dog)
    )
}

@Composable
fun PetDescription(pet: Pet) {
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
        Text(text = "${pet.age} years", fontSize = 12.sp)
    }
}

@Composable
fun PetsTitle() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "All Pets", fontSize = 22.sp)
        SeeAll()
    }
}


@Composable
fun SeeAll() {
    Text(text = "See All", fontSize = 14.sp)
}

