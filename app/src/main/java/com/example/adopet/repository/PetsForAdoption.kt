/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.adopet.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.ui.graphics.Color
import com.example.adopet.model.Gender
import com.example.adopet.model.Pet
import com.example.adopet.model.PetForAdoption
import com.example.adopet.model.PetType
import com.example.adopet.model.Size

val dogRandomImages = listOf(
    "https://images.unsplash.com/photo-1614573171046-19b4dcc2af1e",
    "https://images.unsplash.com/photo-1614344263625-377b98d01eeb",
    "https://images.unsplash.com/photo-1614362520723-18ee20d23aa3"
)

val catRandomImages = listOf(
    "https://images.unsplash.com/photo-1592194996308-7b43878e84a6",
    "https://images.unsplash.com/photo-1614102954362-2afd9d4594e5",
    "https://images.unsplash.com/photo-1613915587574-322c40825be1"
)

val rabbitRandomImages = listOf(
    "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308",
    "https://images.unsplash.com/photo-1607029879874-b24ac9de2eca",
    "https://images.unsplash.com/photo-1606753927971-d1503cb6a849"
)

val hamsterRandomImages = listOf(
    "https://images.unsplash.com/photo-1548767797-d8c844163c4c",
    "https://images.unsplash.com/photo-1612267168669-679c961c5b31",
    "https://images.unsplash.com/photo-1611952310929-e04cea6baeda"
)

fun Gender.icon() = when (this) {
    Gender.MALE -> Icons.Filled.Male
    Gender.FEMALE -> Icons.Filled.Female
}

fun Gender.color() = when (this) {
    Gender.MALE -> Color.Cyan
    Gender.FEMALE -> Color.Magenta
}


val petsForAdoption: List<PetForAdoption> = listOf(
    // DOGS
    PetForAdoption(
        id = 1L,
        pet = Pet(
            id = 1L,
            name = "Lucas",
            age = 2,
            type = PetType.DOG,
            gender = Gender.MALE,
            images = dogRandomImages.shuffled(),
            size = Size.MEDIUM
        ),
        location = "Ann Arbor",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 2L,
        pet = Pet(
            id = 2L,
            name = "Tonny",
            age = 4,
            type = PetType.DOG,
            gender = Gender.MALE,
            images = dogRandomImages.shuffled(),
            size = Size.BIG
        ),
        location = "Atlanta",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 3L,
        pet = Pet(
            id = 3L,
            name = "Mina",
            age = 6,
            type = PetType.DOG,
            gender = Gender.FEMALE,
            images = dogRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Austin",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 4L,
        pet = Pet(
            id = 4L,
            name = "Sun",
            age = 3,
            type = PetType.DOG,
            gender = Gender.FEMALE,
            images = dogRandomImages.shuffled(),
            size = Size.MEDIUM
        ),
        location = "Boulder",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    // CATS
    PetForAdoption(
        id = 5L,
        pet = Pet(
            id = 5L,
            name = "Snow Ball",
            age = 1,
            type = PetType.CAT,
            gender = Gender.FEMALE,
            images = catRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Boulder - Pearl Place",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 6L,
        pet = Pet(
            id = 6L,
            name = "Azrael",
            age = 5,
            type = PetType.CAT,
            gender = Gender.MALE,
            images = catRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Boulder - Walnut",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 7L,
        pet = Pet(
            id = 7L,
            name = "Kitty",
            age = 3,
            type = PetType.CAT,
            gender = Gender.FEMALE,
            images = catRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Cambridge",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    // RABBIT
    PetForAdoption(
        id = 8L,
        pet = Pet(
            id = 8L,
            name = "Snowflake",
            age = 3,
            type = PetType.RABBIT,
            gender = Gender.MALE,
            images = rabbitRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Cambridge",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 9L,
        pet = Pet(
            id = 9L,
            name = "Ry",
            age = 6,
            type = PetType.RABBIT,
            gender = Gender.FEMALE,
            images = rabbitRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Cambridge",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 10L,
        pet = Pet(
            id = 10L,
            name = "Coney",
            age = 1,
            type = PetType.RABBIT,
            gender = Gender.MALE,
            images = rabbitRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Chapel Hill",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    // HAMSTERS
    PetForAdoption(
        id = 11L,
        pet = Pet(
            id = 11L,
            name = "Pinky",
            age = 4,
            type = PetType.HAMSTER,
            gender = Gender.MALE,
            images = hamsterRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Chicago - Carpenter",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 12L,
        pet = Pet(
            id = 12L,
            name = "Cerebro",
            age = 3,
            type = PetType.HAMSTER,
            gender = Gender.MALE,
            images = hamsterRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Chicago - Fulton Market",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
    PetForAdoption(
        id = 13L,
        pet = Pet(
            id = 13L,
            name = "Misty",
            age = 1,
            type = PetType.HAMSTER,
            gender = Gender.MALE,
            images = hamsterRandomImages.shuffled(),
            size = Size.SMALL
        ),
        location = "Mountain View",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a cursus eros. Donec at dapibus justo. In blandit, risus eu fringilla viverra, urna urna varius sapien, in euismod sem neque in erat. Suspendisse convallis scelerisque nisi vel mattis. Donec tempus lectus in metus commodo, et pulvinar ipsum pulvinar. "
    ),
)
