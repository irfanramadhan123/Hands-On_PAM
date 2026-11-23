import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

// Hands-on 3: Producer-Consumer dengan BlockingQueue
// Solusi: Menggunakan LinkedBlockingQueue sebagai jalur komunikasi antar thread.
// Producer memasukkan pesan dengan put(), consumer mengambil dengan take(),
// dan PESAN_SELESAI dipakai sebagai tanda untuk menghentikan consumer.

const val PESAN_SELESAI = "SELESAI"

fun main() {
    // Membuat queue untuk komunikasi antara producer dan consumer
    val queue = LinkedBlockingQueue<String>()

    val producer = thread {
        for (i in 1..5) {
            val pesan = "Pesan #$i"
            // Memasukkan pesan ke queue
            queue.put(pesan)
            println("[Producer] mengirim: $pesan")
            Thread.sleep(200)
        }
        // Mengirim tanda bahwa producer sudah selesai
        queue.put(PESAN_SELESAI)
    }

    val consumer = thread {
        while (true) {
            // Mengambil pesan dari queue, menunggu jika queue masih kosong
            val pesan = queue.take()

            // Menghentikan loop jika menerima tanda selesai
            if (pesan == PESAN_SELESAI) break

            println("[Consumer] menerima: $pesan")
        }
    }

    producer.join()
    consumer.join()
    println("Selesai!")
}
