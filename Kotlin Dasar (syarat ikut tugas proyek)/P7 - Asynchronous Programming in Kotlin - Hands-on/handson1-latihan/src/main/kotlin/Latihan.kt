import kotlinx.coroutines.*

// Hands-on 1: Suspend Function Dasar
// Solusi: Menggunakan delay() di dalam suspend function untuk simulasi panggilan
// server tanpa memblokir thread, lalu memanggilnya dari dalam runBlocking.

suspend fun checkOrderStatus(orderId: String): String {
    // Mensimulasikan network delay selama 2 detik
    delay(2000)
    // Mengembalikan status pesanan
    return "Order $orderId: SHIPPED"
}

fun main() = runBlocking {
    println("Menunggu status pesanan...")

    // Memanggil suspend function dari dalam coroutine runBlocking
    val status = checkOrderStatus("A100")
    println(status)
}
