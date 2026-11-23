package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p9.di.GreetingRepository
import org.koin.compose.koinInject

// Hands-on 3: Dependency Injection dengan Koin
// Solusi: Mengambil GreetingRepository dari container Koin memakai
// koinInject() lalu menampilkan hasil greeting() di layar.

@Composable
fun Handson3Screen() {
    val repository = koinInject<GreetingRepository>()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 3: Dependency Injection dengan Koin")
        Text(repository.greeting())
    }
}
