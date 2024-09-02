package com.example.project_akhir_si

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_akhir_si.Component.ButtonStock
import com.example.project_akhir_si.Component.ItemProfile
import com.example.project_akhir_si.Component.NavBar
import com.example.project_akhir_si.Component.StockProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = { NavBar(navController,modifier = Modifier.fillMaxWidth().height(25.dp)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)  // Apply the paddingValues here
                .background(Color(0xFF5AB2FF))
        ) {
            ItemProfile()
            StockProfile()
            ButtonStock(navController)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
