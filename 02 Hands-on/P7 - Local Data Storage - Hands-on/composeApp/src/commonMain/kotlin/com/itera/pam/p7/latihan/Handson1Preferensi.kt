package com.itera.pam.p7.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p7.db.AppDatabase

// Hands-on 1: "DataStore Preferences Sederhana"
//
// DataStore Preferences versi Multiplatform butuh setup tambahan (okio +
// path provider per-platform), jadi konsep yang sama — menyimpan satu nilai
// key-value yang tetap ada lintas restart aplikasi — disimulasikan memakai
// tabel `Preferensi` pada database SQLDelight (lihat Preferensi.sq).
// Pola read-modify-write-nya identik dengan cara kerja DataStore.
//
// "Jumlah aplikasi dibuka" bertambah 1 setiap kali layar ini dibuka dan
// nilainya persisten: setelah aplikasi ditutup dan dibuka lagi, hitungan
// berlanjut dari nilai terakhir.

private const val KEY_JUMLAH_DIBUKA = "jumlah_dibuka"

@Composable
fun Handson1Screen(database: AppDatabase) {
    var jumlah by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        val queries = database.preferensiQueries
        val nilaiLama = queries.selectByKey(KEY_JUMLAH_DIBUKA)
            .executeAsOneOrNull()?.toLongOrNull() ?: 0L
        val nilaiBaru = nilaiLama + 1
        queries.upsert(KEY_JUMLAH_DIBUKA, nilaiBaru.toString())
        jumlah = nilaiBaru
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Preferensi Sederhana")
        Text("Aplikasi ini sudah dibuka: $jumlah kali")
    }
}
