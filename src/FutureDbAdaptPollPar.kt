import db.DbPollWrapper
import db.FutureDb
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() {
    println("${Instant.now()} starting...")
    val db = DbPollWrapper(FutureDb())
    runBlocking {
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
    }
    println("[${Instant.now()}] done!")
}
