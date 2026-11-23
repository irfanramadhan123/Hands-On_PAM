import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

// Hands-on 1: Race Condition
// Solusi: Menggunakan AtomicInteger agar operasi increment bersifat atomik,
// sehingga dua thread tidak saling menyela saat menambah nilai counter.

class Counter {
    // Menyimpan nilai counter dalam AtomicInteger agar aman diakses banyak thread
    private val c = AtomicInteger(0)

    fun increment() {
        // Menambah nilai secara atomik (baca-ubah-tulis dalam satu operasi)
        c.incrementAndGet()
    }

    fun value(): Int {
        // Membaca nilai terkini dari AtomicInteger
        return c.get()
    }
}

fun main() {
    val counter = Counter()
    val iterasi = 100_000

    val t1 = thread {
        repeat(iterasi) { counter.increment() }
    }
    val t2 = thread {
        repeat(iterasi) { counter.increment() }
    }

    t1.join()
    t2.join()

    println("Hasil akhir: ${counter.value()} (seharusnya ${iterasi * 2})")
}
