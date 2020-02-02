import kotlinx.coroutines.delay
import java.time.Instant

suspend fun main() {
    println("[${Instant.now()}] Hello, World!")
    delay(1000)
    println("[${Instant.now()}] Goodbye, World!")
}