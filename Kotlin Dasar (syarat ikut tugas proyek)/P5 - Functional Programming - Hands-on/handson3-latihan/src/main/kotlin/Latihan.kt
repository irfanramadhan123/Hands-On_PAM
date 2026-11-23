// Hands-on 3: Closure — Counter Factory


// Solusi: Closure yang menyimpan state `count` masing-masing counter.

fun makeCounter(): () -> Int {
    // menginisiaasi variabel state lokal di scope terluar
    var count = 0

    // mengembalikan lambda yang menangkap dan menambah nilai count
    return {
        ++count
    }
}

fun main() {
    val counterA = makeCounter()
    val counterB = makeCounter()

    println(counterA()) // 1
    println(counterA()) // 2
    println(counterA()) // 3

    println(counterB()) // 1 (counterB independen dari counterA)
    println(counterB()) // 2
}
