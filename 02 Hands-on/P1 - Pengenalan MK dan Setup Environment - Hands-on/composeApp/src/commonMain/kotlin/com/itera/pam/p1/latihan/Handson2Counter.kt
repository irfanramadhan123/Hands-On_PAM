package com.itera.pam.p1.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Hands-on 2: State & Recomposition — Counter
// Counter sederhana dengan tombol tambah (+) dan kurang (-). State disimpan
// dengan remember { mutableStateOf(...) } agar UI recompose saat nilainya berubah.

@Composable
fun Handson2Screen() {
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: Counter")
        Text("Nilai: $count")

        Row {
            Button(onClick = { count++ }) {
                Text("+")
            }
            Button(onClick = { count-- }) {
                Text("-")
            }
        }
    }
}
