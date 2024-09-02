package com.example.project_akhir_si.data
import com.example.project_akhir_si.Component.ItemMaterialUpdate
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.project_akhir_si.Component.ItemMaterial

import com.google.firebase.firestore.FirebaseFirestore


class DBase {
    fun saveProductsToFirestore(product: Product, onResult: (Boolean) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val newDocRef = db.collection("products").document()
        val productWithId = product.copy(id = newDocRef.id)
        newDocRef.set(productWithId)
            .addOnSuccessListener {
                Log.d("Firestore", "DocumentSnapshot added with ID: ${newDocRef.id}")
                onResult(true)
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
                onResult(false)
            }
    }
}

    @Composable
    fun ListItem(db: FirebaseFirestore) {
        var products by remember { mutableStateOf(listOf<Product>()) }

        LaunchedEffect(Unit) {
            db.collection("products")
                .get()
                .addOnSuccessListener { result ->
                    products = result.toObjects(Product::class.java)
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                }
        }

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(products) { product ->
                ItemMaterial(product)
            }
        }
    }

@Composable
fun ListItemUpdate(db: FirebaseFirestore, navController: NavController) {
    var products by remember { mutableStateOf(listOf<Product>()) }

    LaunchedEffect(Unit) {
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                products = result.toObjects(Product::class.java)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(products) { product ->
            ItemMaterialUpdate(product, db)
        }
    }
}