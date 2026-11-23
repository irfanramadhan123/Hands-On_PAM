// Hands-on 1: Try-Catch-Finally Dasar
// Solusi: Pembagian dibungkus try, ArithmeticException ditangkap dan
// dikembalikan nilai default 0, finally selalu mencetak log selesai.

fun safeDivide(a: Int, b: Int): Int {
    return try {
        a / b
    } catch (e: ArithmeticException) {
        println("Error: tidak bisa membagi $a dengan $b (${e.message})")
        0
    } finally {
        println("safeDivide($a, $b) selesai diproses")
    }
}

fun main() {
    println("Hasil 1: ${safeDivide(10, 2)}")  // sukses -> 5
    println("Hasil 2: ${safeDivide(10, 0)}")  // b == 0 -> ditangani, hasil 0
    println("Hasil 3: ${safeDivide(9, 3)}")   // sukses -> 3
}
