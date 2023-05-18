package com.ingmicha.android.udemy.novo.compose.mvi.view

import com.ingmicha.android.udemy.novo.compose.mvi.model.Animal

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Animals(val animal: List<Animal>) : MainState()
    data class Error(val error: String) : MainState()

}