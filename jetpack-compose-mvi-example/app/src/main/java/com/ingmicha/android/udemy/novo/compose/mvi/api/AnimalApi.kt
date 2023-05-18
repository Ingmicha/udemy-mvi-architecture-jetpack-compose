package com.ingmicha.android.udemy.novo.compose.mvi.api

import com.ingmicha.android.udemy.novo.compose.mvi.model.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>

}