import kotlinx.coroutines.*

// Hands-on 1: Coroutines Dasar
// Mengambil data dari 2 sumber secara PARALEL menggunakan async/await,
// lalu menggabungkan hasilnya. Waktu eksekusi mendekati 1000ms (waktu
// fetch terlama), bukan ~1800ms seperti jika dijalankan secara sequential.

suspend fun fetchUserProfile(userId: String): String {
    delay(1000) // Simulasi network delay
    return "User: John Doe"
}

suspend fun fetchUserPosts(userId: String): List<String> {
    delay(800) // Simulasi network delay
    return listOf("Post 1", "Post 2", "Post 3")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val profileDeferred = async { fetchUserProfile("user-1") }
    val postsDeferred = async { fetchUserPosts("user-1") }

    val profile = profileDeferred.await()
    val posts = postsDeferred.await()

    println(profile)
    println("Posts: $posts")

    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}
