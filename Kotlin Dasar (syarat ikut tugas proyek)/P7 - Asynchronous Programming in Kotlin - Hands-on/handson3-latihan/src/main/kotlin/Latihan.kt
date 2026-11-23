import kotlinx.coroutines.*

// Hands-on 3: Structured Concurrency & Cancellation
// Solusi: Menggunakan satu Job induk sebagai parent dari semua download. Saat
// parentJob dibatalkan, semua child coroutine yang belum selesai ikut berhenti.

class DownloadManager(private val scope: CoroutineScope) {

    fun downloadFile(name: String, durationMs: Long): Job {
        // Membuat child coroutine baru di dalam scope milik parentJob
        val job = scope.launch {
            // Mensimulasikan proses download
            delay(durationMs)
            println("$name selesai di-download")
        }
        return job
    }
}

fun main() = runBlocking {
    // Membuat Job induk untuk semua proses download
    val parentJob = Job()

    // Membuat scope baru yang terikat dengan parentJob
    val scope = CoroutineScope(parentJob)

    val manager = DownloadManager(scope)

    manager.downloadFile("foto.jpg", 1000)
    manager.downloadFile("video.mp4", 3000)
    manager.downloadFile("dokumen.pdf", 1500)

    delay(1200) // Beri waktu foto.jpg dan dokumen.pdf selesai

    println("Membatalkan sisa download...")
    // Membatalkan parentJob beserta semua child yang masih berjalan
    parentJob.cancel()

    delay(2000) // Tunggu untuk membuktikan video.mp4 memang tidak selesai
    println("Selesai.")
}
