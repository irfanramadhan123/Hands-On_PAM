import kotlinx.coroutines.*

// Hands-on 2: launch vs async
// Solusi: Menggunakan launch untuk mencatat log karena tidak butuh nilai balik,
// dan async untuk menghitung total harga karena hasilnya perlu ditampilkan.

suspend fun writeAccessLog(message: String) {
    delay(500)
    println("[LOG] $message")
}

suspend fun calculateTotalPrice(prices: List<Int>): Int {
    delay(700)
    return prices.sum()
}

fun main() = runBlocking {
    val prices = listOf(15000, 25000, 10000)

    // Menjalankan pencatatan log secara fire-and-forget
    launch { writeAccessLog("Checkout dimulai") }

    // Menjalankan perhitungan total dan menyimpan Deferred-nya
    val totalDeferred = async { calculateTotalPrice(prices) }

    // Menunggu hasil perhitungan lalu menampilkannya
    val total = totalDeferred.await()
    println("Total belanja: Rp$total")
}
