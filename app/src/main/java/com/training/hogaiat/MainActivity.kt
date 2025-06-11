package com.training.hogaiat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import com.training.hogaiat.screens.ExcuseScreen
import com.training.hogaiat.screens.NutritionScreen
import com.training.hogaiat.screens.SupervisorTaskScreen
import com.training.hogaiat.ui.theme.HogaiatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HogaiatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("أصحاب الأعذار") },
                    selected = navController.currentDestination?.route == "excuse",
                    onClick = { navController.navigate("excuse") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text("التغذية") },
                    selected = navController.currentDestination?.route == "nutrition",
                    onClick = { navController.navigate("nutrition") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = null) },
                    label = { Text("مهام المشرف") },
                    selected = navController.currentDestination?.route == "tasks",
                    onClick = { navController.navigate("tasks") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "excuse",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("excuse") { ExcuseScreen() }
            composable("nutrition") { NutritionScreen() }
            composable("tasks") { SupervisorTaskScreen() }
        }
    }
}

