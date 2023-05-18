package com.ingmicha.android.udemy.novo.compose.mvi.api

import com.ingmicha.android.udemy.novo.compose.mvi.model.Animal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimalService @Inject constructor(private val animalApi: AnimalApi) {
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

    suspend fun doFetchAnimals(): List<Animal> {
        return withContext(defaultDispatcher) {
            val response = animalApi.getAnimals()
            response
        }
    }
}