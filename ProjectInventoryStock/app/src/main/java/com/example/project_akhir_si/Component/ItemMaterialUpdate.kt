package com.example.project_akhir_si.Component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.project_akhir_si.data.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemMaterialUpdate(product: Product, db: FirebaseFirestore) {
    var amountIn by remember { mutableStateOf("") }
    var amountOut by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
        ) {
            Text(text = "Detail Produk")
            Text(text = "Nama Produk: ${product.name}")
            Text(text = "Harga: ${product.price}")

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
            ) {
                Text("Produk Masuk: ")
                OutlinedTextField(
                    value = amountIn,
                    onValueChange = { amountIn = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF396EB0),
                        contentColor = Color.White
                    ),
                    onClick = {
                        if (amountIn.isNotBlank()) {
                            val newAmount = product.amountOfProduct + amountIn.toInt()
                            updateProductAmount(product.id, newAmount, db, snackbarHostState, coroutineScope)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Tambah")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
            ) {
                Text("Produk Keluar: ")
                OutlinedTextField(
                    value = amountOut,
                    onValueChange = { amountOut = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF396EB0),
                        contentColor = Color.White
                    ),
                    onClick = {
                        if (amountOut.isNotBlank()) {
                            val newAmount = product.amountOfProduct - amountOut.toInt()
                            if (newAmount >= 0) {
                                updateProductAmount(product.id, newAmount, db, snackbarHostState, coroutineScope)
                            } else {
                                // Handle error (optional)
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Kurang")
                }
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF396EB0),
                    contentColor = Color.White
                ),
                onClick = {
                    deleteProduct(product.id, db, snackbarHostState, coroutineScope)
                },
                modifier = Modifier
                    .align(Alignment.End) // Align button to the end (right)
                    .padding(top = 8.dp)
            ) {
                Text("Hapus")
            }
        }
    }

    SnackbarHost(hostState = snackbarHostState)
}

private fun updateProductAmount(
    productId: String,
    newAmount: Int,
    db: FirebaseFirestore,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    db.collection("products").document(productId)
        .update("amountOfProduct", newAmount)
        .addOnSuccessListener {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Product amount successfully updated!")
            }
        }
        .addOnFailureListener { e ->
            println("Error updating product amount: $e")
            // Handle error (optional)
        }
}

private fun deleteProduct(
    productId: String,
    db: FirebaseFirestore,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    db.collection("products").document(productId)
        .delete()
        .addOnSuccessListener {
            // Deletion success (optional)
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Product successfully deleted!")
            }
        }
        .addOnFailureListener { e ->
            println("Error deleting product: $e")
            // Handle error (optional)
        }
}
