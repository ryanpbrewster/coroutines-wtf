import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import java.time.Instant

suspend fun main() {
    withTimeout(5000L) {
        while (true) {
            delay(500)
            println(Instant.now())
        }
    }
}