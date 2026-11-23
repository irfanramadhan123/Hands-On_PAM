import java.util.concurrent.Callable
import java.util.concurrent.Executors

// Hands-on 2: ExecutorService & Future
// Solusi: Menggunakan thread pool berisi 4 pekerja agar keempat task berjalan
// paralel, lalu mengumpulkan hasilnya lewat Future.get().

fun hitungKuadrat(n: Int): Int {
    Thread.sleep(1000) // Simulasi kerja berat
    return n * n
}

fun main() {
    val angka = listOf(1, 2, 3, 4)
    val startTime = System.currentTimeMillis()

    // Membuat thread pool dengan 4 pekerja
    val executor = Executors.newFixedThreadPool(4)

    // Mengirim satu Callable per angka ke executor dan menyimpan Future-nya
    val futures = angka.map { n -> executor.submit(Callable { hitungKuadrat(n) }) }

    // Mengambil semua hasil, get() menunggu sampai task selesai
    val hasil = futures.map { it.get() }
    println("Hasil: $hasil")

    // Mematikan executor agar JVM bisa berhenti
    executor.shutdown()

    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}
