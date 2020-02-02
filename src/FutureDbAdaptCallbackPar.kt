import db.FutureDb
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() {
    println("${Instant.now()} starting...")
    val db = FutureDb()
    runBlocking {
        coroutineScope {
            launch { db.set("hello", "world").await() }
            launch { db.set("color", "blue").await() }
            launch { db.set("day", "wednesday").await() }
        }
        coroutineScope {
            launch { db.get("hello").await() }
            launch { db.get("color").await() }
            launch { db.get("day").await() }
            launch { db.get("asdf").await() }
        }
    }
    println("[${Instant.now()}] done!")
}
