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
import com.itera.pam.p9.getPlatformName
import com.itera.pam.p9.vibrate

// Hands-on 1: expect/actual Lanjutan — Getar Perangkat
// Solusi: Memanggil vibrate() (expect/actual) dari tombol, menampilkan
// platform yang sedang berjalan dan penjelasan perilaku getar per platform.

@Composable
fun Handson1Screen() {
    var jumlahGetar by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Getar Perangkat (expect/actual)")
        Text("Platform: ${getPlatformName()}")
        Button(onClick = {
            vibrate()
            jumlahGetar++
        }) {
            Text("Getar!")
        }
        Text("Tombol getar ditekan $jumlahGetar kali")
        Text(
            "Catatan: Android & iOS bergetar sungguhan, sedangkan Desktop " +
                "hanya mencetak log ke console (no-op)."
        )
    }
}
