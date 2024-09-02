package com.example.project_akhir_si

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.project_akhir_si.data.DBase
import com.example.project_akhir_si.data.Product

@Composable
fun AddProduct(navController: NavController) {
    val dBase = DBase()
    AddProductScreen(navController = navController) { product ->
        dBase.saveProductsToFirestore(product) { result ->
            if (result) {
                // Handle success, e.g., navigate back or show a success message
                navController.popBackStack()
            } else {
                // Handle failure, e.g., show an error message
            }
        }
    }
}
