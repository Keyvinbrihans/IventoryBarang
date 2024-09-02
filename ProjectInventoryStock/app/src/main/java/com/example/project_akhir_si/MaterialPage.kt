package com.example.project_akhir_si

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.project_akhir_si.Component.NavBar
import com.example.project_akhir_si.Component.SearchBar
import com.example.project_akhir_si.data.ListItem
import com.google.firebase.firestore.FirebaseFirestore

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialPage(navController: NavController, db: FirebaseFirestore) {
    Scaffold(
        bottomBar = {
            NavBar(navController, modifier = Modifier
                .fillMaxWidth()
                .height(25.dp))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF5AB2FF)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Routes.addproductscreen) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF396EB0),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(80.dp, 50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Add Stuff",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF396EB0),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(80.dp, 50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = "More",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            ListItem(db)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
