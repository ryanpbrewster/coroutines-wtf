import db.DbPollWrapper
import db.FutureDb
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() {
    println("${Instant.now()} starting...")
    val db = DbPollWrapper(FutureDb())
    runBlocking {
        db.set("hello", "world")
        db.set("color", "blue")
        db.set("day", "wednesday")
        db.get("hello")
        db.get("color")
        db.get("day")
        db.get("asdf")
    }
    println("[${Instant.now()}] done!")
}
