import db.FakeDb
import kotlinx.coroutines.*
import java.time.Instant


fun main() = runBlocking {
    println("${Instant.now()} starting...")
    val db = FakeDb()
    coroutineScope {
        launch { db.set("hello", "world") }
        launch { db.set("color", "blue") }
        launch { db.set("day", "wednesday") }
    }
    coroutineScope {
        launch { db.get("hello") }
        launch { db.get("color") }
        launch { db.get("day") }
        launch { db.get("asdf") }
    }
    println("[${Instant.now()}] done!")
}
