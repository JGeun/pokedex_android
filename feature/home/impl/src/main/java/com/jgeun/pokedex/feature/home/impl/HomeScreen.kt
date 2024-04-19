package com.jgeun.pokedex.feature.home.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeRoute(
    onNavigateToDetail: () -> Unit
) {
    HomeScreen(
        onNavigateToDetail = onNavigateToDetail
    )
}

@Composable
fun HomeScreen(
    onNavigateToDetail: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(
            modifier = Modifier.clickable {
                onNavigateToDetail()
            },
            text = "HomeScreen",
            color = Color.White,
            fontSize = 24.sp
        )
    }
}