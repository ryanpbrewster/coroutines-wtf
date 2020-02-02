import db.FutureDb
import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() {
    println("${Instant.now()} starting...")
    val db = FutureDb()
    runBlocking {
        db.set("hello", "world").await()
        db.set("color", "blue").await()
        db.set("day", "wednesday").await()
        db.get("hello").await()
        db.get("color").await()
        db.get("day").await()
        db.get("asdf").await()
    }
    println("[${Instant.now()}] done!")
}
