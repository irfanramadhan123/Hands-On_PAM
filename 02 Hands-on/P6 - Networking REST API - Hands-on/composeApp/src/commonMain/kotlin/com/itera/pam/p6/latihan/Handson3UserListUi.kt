package com.itera.pam.p6.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Hands-on 3: Tampilkan Data di Compose dengan Loading/Error UI
// UserListStateHolder (dari Handson2Repository.kt) dipakai untuk menampilkan:
// - CircularProgressIndicator() selagi UiState.Loading
// - LazyColumn berisi nama & email user selagi UiState.Success
// - Pesan error + tombol "Coba Lagi" (memanggil ulang load()) selagi UiState.Error

@Composable
fun Handson3Screen() {
    val holder = remember { UserListStateHolder() }
    val state by holder.state.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) { holder.load() }

    Column(modifier = Modifier.padding(16.dp)) {
        when (val s = state) {
            UiState.Loading -> CircularProgressIndicator()

            // Tinggi LazyColumn dibatasi karena App membungkus layar dengan
            // Column yang bisa di-scroll (tingginya tidak terbatas).
            is UiState.Success -> LazyColumn(
                modifier = Modifier.fillMaxWidth().heightIn(max = 500.dp)
            ) {
                items(s.users, key = { it.id }) { user ->
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(user.name, fontWeight = FontWeight.Bold)
                        Text(user.email, color = Color.Gray)
                    }
                }
            }

            is UiState.Error -> {
                Text("Error: ${s.message}")
                Button(onClick = { scope.launch { holder.load() } }) {
                    Text("Coba Lagi")
                }
            }
        }
    }
}
