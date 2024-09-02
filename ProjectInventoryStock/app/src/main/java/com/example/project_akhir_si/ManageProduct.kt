package com.example.project_akhir_si

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.project_akhir_si.Component.NavBar
import com.example.project_akhir_si.Component.SearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.example.project_akhir_si.data.ListItemUpdate
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageProduct(navController: NavController) {
    val db = FirebaseFirestore.getInstance()

    Scaffold(
        bottomBar = {
            NavBar(
                navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFF5AB2FF)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar()
            ListItemUpdate(db = db, navController = navController)
        }
    }
}
