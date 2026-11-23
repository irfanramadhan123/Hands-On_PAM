package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
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
import com.itera.pam.p9.hasCameraPermission

// Hands-on 2: Permission Check (Platform-Aware)
// Solusi: Menampilkan status izin kamera dari hasCameraPermission()
// (cek asli di Android, stub true di iOS/Desktop) dengan pesan berbeda
// untuk true/false. Tombol hanya memeriksa ulang status izin.

@Composable
fun Handson2Screen() {
    var izinDiberikan by remember { mutableStateOf(hasCameraPermission()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: Permission Check")
        Text("Status izin kamera: ${if (izinDiberikan) "DIBERIKAN" else "DITOLAK"}")
        Text(
            if (izinDiberikan) {
                "Kamera siap digunakan."
            } else {
                "Izin kamera belum diberikan. Aktifkan lewat Pengaturan > Aplikasi > Izin."
            }
        )
        Button(onClick = { izinDiberikan = hasCameraPermission() }) {
            Text("Simulasikan Minta Izin")
        }
    }
}
