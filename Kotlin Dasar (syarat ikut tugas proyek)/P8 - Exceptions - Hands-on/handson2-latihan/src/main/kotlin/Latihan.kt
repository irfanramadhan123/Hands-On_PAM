// Hands-on 2: Custom Exception & Exception Hierarchy
// Solusi: Membuat InsufficientBalanceException (turunan Exception) yang
// dilempar saat saldo tidak cukup, lalu ditangkap secara spesifik di main().

class InsufficientBalanceException(message: String) : Exception(message)

class BankAccount(private val owner: String, startBalance: Double) {
    var balance: Double = startBalance
        private set

    fun deposit(amount: Double) {
        require(amount > 0) { "Jumlah deposit harus lebih besar dari 0" }
        balance += amount
    }

    fun withdraw(amount: Double) {
        require(amount > 0) { "Jumlah penarikan harus lebih besar dari 0" }

        if (amount > balance) {
            throw InsufficientBalanceException(
                "Saldo $owner tidak cukup: saldo $balance, diminta $amount"
            )
        }
        balance -= amount
    }
}

fun main() {
    val account = BankAccount("Andi", 100_000.0)

    account.deposit(50_000.0)
    println("Saldo setelah deposit: ${account.balance}")

    try {
        account.withdraw(500_000.0)
    } catch (e: InsufficientBalanceException) {
        println("Penarikan gagal: ${e.message}")
    }

    account.withdraw(30_000.0)
    println("Saldo akhir: ${account.balance}")
}
