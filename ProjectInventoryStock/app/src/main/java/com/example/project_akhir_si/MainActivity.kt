package com.example.project_akhir_si

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Routes.loginPage) {
                composable(Routes.loginPage) {
                    LoginPage(navController)
                }
                composable(Routes.signupPage) {
                    SignupPage(navController)
                }
                composable(Routes.homescreen) {
                    HomeScreen(navController)
                }
                composable(Routes.materialpage) {
                    MaterialPage(navController = navController, db = db)
                }
                composable(Routes.profilescreen) {
                    ProfileScreen(navController)
                }
                composable(Routes.addproductscreen) {
                    AddProduct(navController)
                }
                composable(Routes.manageproduct) {
                    ManageProduct(navController)
                }
                composable(Routes.detailscreenproductin) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId")
                    DetailScreen(navController = navController, productId = productId, db = db)
                }
            }
        }
    }
}
