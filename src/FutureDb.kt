import db.FutureDb
import java.time.Instant

fun main() {
    println("${Instant.now()} starting...")
    val db = FutureDb()
    db.set("hello", "world").get()
    db.set("color", "blue").get()
    db.set("day", "wednesday").get()
    db.get("hello").get()
    db.get("color").get()
    db.get("day").get()
    db.get("asdf").get()
    println("[${Instant.now()}] done!")
}
