package com.jgeun.pokedex.feature.detail.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun DetailRouter() {
    DetailScreen()
}

@Composable
fun DetailScreen(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(
            text = "DetailScreen",
            color = Color.White,
            fontSize = 24.sp
        )
    }
}