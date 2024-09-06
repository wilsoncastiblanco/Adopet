package com.servall.adopet.ui.pets

import androidx.annotation.PluralsRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.servall.adopet.R
import com.servall.adopet.model.Pet
import com.servall.adopet.repository.color
import com.servall.adopet.repository.icon

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PetDetail(
    navigateUp: () -> Unit,
    petId: String,
    petsDetailViewModel: PetDetailViewModel = viewModel(
        factory = PetDetailViewModelFactory(petId)
    )
) {
    val petDetailUiState by petsDetailViewModel.petDetailUiState.collectAsStateWithLifecycle()
    PetDetailContent(
        petId = petId,
        navigateUp = navigateUp,
        onFavorite = petsDetailViewModel::saveFavorite,
        petDetailUiState = petDetailUiState
    )
}

@Composable
fun PetDetailContent(
    petId: String,
    navigateUp: () -> Unit,
    onFavorite: (String) -> Unit,
    petDetailUiState: PetDetailUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = petDetailUiState.backgroundColor(),
                navigationIcon = { Up(upPress = navigateUp) },
                actions = {
                    IconButton(
                        onClick = { onFavorite(petId) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (petDetailUiState.isFavorite()) Color.Red else Color.Gray
                        )
                    }
                }
            )
        }

    ) { padding ->
        when (petDetailUiState) {
            is PetDetailUiState.Error -> Text("Error! ${petDetailUiState.message}")
            PetDetailUiState.Loading -> CircularProgressIndicator()
            is PetDetailUiState.Success -> {
                PetDetail(petDetailUiState.pet, upPress = navigateUp)
            }

            else -> {}
        }
    }
}

private fun PetDetailUiState.isFavorite(): Boolean {
    val result = this as? PetDetailUiState.Success ?: return false
    return result.isFavorite
}

@Composable
fun PetDetailUiState.backgroundColor(): Color {
    return if (this is PetDetailUiState.Success) {
        pet.gender.color().copy(alpha = 0.6f)
    } else {
        MaterialTheme.colors.background
    }
}

@Composable
fun PetDetail(
    pet: Pet,
    upPress: () -> Unit
) {
    Surface(color = pet.gender.color().copy(alpha = 0.6f)) {
        val scroll = rememberScrollState(0)
        Column(Modifier.fillMaxSize()) {
            Header(scroll.value, pet)
            Body(scroll, pet)
        }
    }
}

@Composable
fun Body(scrollState: ScrollState, pet: Pet) {
    Surface(
        Modifier
            .fillMaxHeight()
            .clip(
                shape = RoundedCornerShape(
                    topStart = 26.dp,
                    topEnd = 26.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
            .background(Color.White),
        elevation = 14.dp
    ) {
        Column(
            Modifier
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "A fluffy bundle of joy, this playful pup is always up for an adventure. With a wagging tail and sparkling eyes, they're sure to brighten your day.",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 4.dp),
            )
            Spacer(Modifier.height(8.dp))
            PuppyAdditionalDetail(pet)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun PuppyAdditionalDetail(pet: Pet) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.detail_puppy_gender),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = pet.gender.icon(),
                    tint = pet.gender.color(),
                    contentDescription = null
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(70.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null
                )
                Text(
                    text = "Bogotá, Colombia",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Light,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.detail_puppy_age),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = quantityStringResource(
                        R.plurals.detail_puppy_age_plural,
                        pet.age,
                        pet.age
                    ),
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.detail_puppy_size),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = pet.size.name,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun quantityStringResource(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String {
    return LocalContext.current.resources.getQuantityString(id, quantity, *formatArgs)
}

@Composable
fun Header(scrollValue: Int, pet: Pet) {

    val maxOffsetDp = WindowInsets.navigationBars.asPaddingValues(LocalDensity.current)
    val minOffsetDp = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)

    val offsetDp =
        (maxOffsetDp.calculateTopPadding() - scrollValue.dp).coerceAtLeast(minOffsetDp.calculateTopPadding())

    BoxWithConstraints(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {

        Column {
            Text(
                text = pet.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(start = 4.dp),
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
            )

            val halfScreenSize = this@BoxWithConstraints.maxWidth.value / 2
            val rowState = rememberLazyListState(
                initialFirstVisibleItemIndex = pet.images.size / 2,
                initialFirstVisibleItemScrollOffset = -(halfScreenSize).toInt()
            )

            LazyRow(state = rowState) {
                items(pet.images) { imageUrl ->
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        elevation = 8.dp,
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = pet.name,
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.ic_dog)
                        )
                    }
                }
            }
        }
    }
}

@Composable
 fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = MaterialTheme.colors.surface,
            contentDescription = "Back"
        )
    }
}