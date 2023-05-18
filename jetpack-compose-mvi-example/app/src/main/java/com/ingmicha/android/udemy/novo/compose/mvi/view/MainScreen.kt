package com.ingmicha.android.udemy.novo.compose.mvi.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ingmicha.android.udemy.novo.compose.mvi.api.AnimalService
import com.ingmicha.android.udemy.novo.compose.mvi.core.di.NetworkModule.Companion.BASE_URL
import com.ingmicha.android.udemy.novo.compose.mvi.model.Animal

@Composable
fun MainScreen(mainViewModel: MainViewModel, onButtonClick: () -> Unit) {
    val state = mainViewModel.state.value

    val localContext = LocalContext.current

    when (state) {
        is MainState.Idle -> IdleScreen(onButtonClick)
        is MainState.Animals -> AnimalsList(animals = state.animal)
        is MainState.Error -> {
            IdleScreen(onButtonClick)
            Toast.makeText(localContext, state.error, Toast.LENGTH_LONG).show()
        }

        MainState.Loading -> LoadingScreen()
    }
}

@Composable
fun IdleScreen(onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Animals")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun AnimalsList(animals: List<Animal>) {
    LazyColumn {
        items(animals) {
            AnimalItem(animal = it)
            Divider(color = Color.Gray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AnimalItem(animal: Animal) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val url = BASE_URL + animal.image
        val painter = rememberImagePainter(data = url)

        Image(
            painter = painter, contentDescription = "animal image",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp)
        ) {
            Text(text = animal.name, fontWeight = FontWeight.Bold)
            Text(text = animal.location)
        }
    }
}