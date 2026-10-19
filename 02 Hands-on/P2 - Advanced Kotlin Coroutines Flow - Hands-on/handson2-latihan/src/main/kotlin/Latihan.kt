import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

// Hands-on 2: Flow dengan Operators
// Flow yang mensimulasikan sensor suhu, memfilter suhu di atas 30°C,
// lalu menampilkan pesan warning untuk setiap suhu tinggi.

fun temperatureSensor(): Flow<Int> = flow {
    repeat(10) {
        delay(500)
        val temp = Random.nextInt(20, 40) // Random 20-39°C
        emit(temp)
    }
}

fun main() = runBlocking {
    temperatureSensor()
        .filter { it > 30 }
        .map { "⚠️ WARNING: Suhu tinggi terdeteksi: ${it}°C" }
        .collect { println(it) }
}
