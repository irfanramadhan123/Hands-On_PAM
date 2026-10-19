package com.itera.pam.p6.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Hands-on 2: Repository Pattern & Loading State
// fetchUsers() (dari Handson1KtorClient.kt) dibungkus di balik Repository,
// lalu state holder merepresentasikan status pengambilan data:
// Loading / Success / Error.
//
// Catatan desain: state holder ini sengaja berupa class biasa (bukan
// androidx.lifecycle.ViewModel) supaya hands-on ini tetap fokus ke konsep
// networking/repository — pola ViewModel yang lebih lengkap dipelajari
// di Pertemuan 4 (State Management MVVM).

class UserRepository {
    suspend fun getUsers(): List<RemoteUser> = fetchUsers()
}

sealed class UiState {
    data object Loading : UiState()
    data class Success(val users: List<RemoteUser>) : UiState()
    data class Error(val message: String) : UiState()
}

class UserListStateHolder(
    private val repository: UserRepository = UserRepository()
) {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    suspend fun load() {
        _state.value = UiState.Loading
        try {
            val users = repository.getUsers()
            _state.value = UiState.Success(users)
        } catch (e: Exception) {
            _state.value = UiState.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}

@Composable
fun Handson2Screen() {
    val holder = remember { UserListStateHolder() }
    val state by holder.state.collectAsState()

    LaunchedEffect(Unit) { holder.load() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: Repository Pattern & Loading State")
        when (val s = state) {
            UiState.Loading -> Text("State: Loading")
            is UiState.Success -> Text("State: Success (${s.users.size} user)")
            is UiState.Error -> Text("State: Error — ${s.message}")
        }
    }
}
