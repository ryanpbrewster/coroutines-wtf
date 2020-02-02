import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant

// coroutineScope introduces a CoroutineScope and does not yield control until
// all of the inner coroutines have completed.
suspend fun main() = coroutineScope {
    for (id in 'a'..'d') {
        println("[${Instant.now()}] launching $id")
        launch {
            for (i in 1..10) {
                delay(100L * i)
                println("$id$i")
            }
        }
    }
}
