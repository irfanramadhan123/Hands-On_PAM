// Hands-on 3: Kotlin Sugar - try sebagai expression, require(), error(), multi-catch
// Solusi: Parser tiket "nama/umur" memakai require() untuk validasi,
// try sebagai expression + error() untuk parsing umur, dan multi-catch di main().

data class Tiket(val nama: String, val umur: Int)

fun parseTiket(input: String): Tiket {
    val bagian = input.split("/")

    require(bagian.size == 2) { "Format harus 'nama/umur', diterima: $input" }

    val nama = bagian[0]

    val umur: Int = try {
        bagian[1].trim().toInt()
    } catch (e: NumberFormatException) {
        error("Umur tidak valid: ${bagian[1]}")
    }

    require(nama.isNotBlank()) { "Nama tidak boleh kosong, diterima: $input" }
    require(umur >= 0) { "Umur tidak boleh negatif: $umur" }

    return Tiket(nama, umur)
}

fun main() {
    val inputs = listOf("Budi/25", "Siti/17", "format-salah", "Andi/bukan-angka", "/30")

    for (input in inputs) {
        try {
            println("Berhasil: ${parseTiket(input)}")
        } catch (e: IllegalArgumentException) {
            println("Input tidak valid -> ${e.message}")
        } catch (e: IllegalStateException) {
            println("Gagal parsing -> ${e.message}")
        }
    }
}
