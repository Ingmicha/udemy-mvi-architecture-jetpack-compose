package com.ingmicha.android.udemy.novo.compose.mvi.api

import javax.inject.Inject

class AnimalRepo @Inject constructor(private val api: AnimalService) {
    suspend fun getAnimals() = api.doFetchAnimals()
}