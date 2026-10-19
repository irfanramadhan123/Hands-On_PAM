package com.itera.pam.p5.latihan

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// Hands-on 3: Bottom Navigation
// Tiga tab (Home/Search/Profil) dengan NavigationBar yang terhubung ke NavHost.
// Saat pindah tab, back stack di-pop sampai start destination (popUpTo) dengan
// state tab disimpan (saveState), tidak membuat salinan tab yang sama
// (launchSingleTop), dan state tab dipulihkan saat dibuka lagi (restoreState).
// Hasilnya back stack tidak menumpuk dan tombol Back kembali ke tab Home.

private data class Tab(val route: String, val label: String)

private val tabs = listOf(
    Tab("home", "Home"),
    Tab("search", "Search"),
    Tab("profil", "Profil"),
)

@Composable
fun Handson3Screen() {
    val navController = rememberNavController()

    // Tinggi Scaffold ditetapkan karena App membungkus layar dengan Column
    // yang bisa di-scroll (tingginya tidak terbatas).
    Scaffold(
        modifier = Modifier.fillMaxWidth().height(500.dp),
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { Text("Halaman Home") }
            composable("search") { Text("Halaman Search") }
            composable("profil") { Text("Halaman Profil") }
        }
    }
}
