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

import com.example.adopet.R
import com.example.adopet.model.PetCategory
import com.example.adopet.model.PetType

val petCategories: List<PetCategory> = listOf(
    PetCategory(
        id = 1L,
        name = "Dogs",
        icon = PetIcon.dog,
        type = PetType.DOG
    ),
    PetCategory(
        id = 2L,
        name = "Cats",
        icon = PetIcon.cat,
        type = PetType.CAT
    ),
    PetCategory(
        id = 4L,
        name = "Hamster",
        icon = PetIcon.mouse,
        type = PetType.HAMSTER
    ),
    PetCategory(
        id = 5L,
        name = "Rabbits",
        icon = PetIcon.rabbit,
        type = PetType.RABBIT
    ),
    PetCategory(
        id = 6L,
        name = "Pigs",
        icon = PetIcon.pig,
        type = PetType.PIG
    )
)

object PetIcon {
    val dog = R.drawable.ic_dog
    val cat = R.drawable.ic_cat
    val mouse = R.drawable.ic_mouse
    val rabbit = R.drawable.ic_rabbit
    val pig = R.drawable.ic_pig
}
