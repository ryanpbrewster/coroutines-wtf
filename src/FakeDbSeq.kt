import db.FakeDb
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    println("${Instant.now()} starting...")
    val db = FakeDb()
    db.set("hello", "world")
    db.set("color", "blue")
    db.set("day", "wednesday")
    db.get("hello")
    db.get("color")
    db.get("day")
    db.get("asdf")
    println("[${Instant.now()}] done!")
}

