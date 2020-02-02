import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch(Dispatchers.IO) { blocker("a") }
        launch(Dispatchers.IO) { blocker("b") }
    }
}

suspend fun blocker(id: String) {
    println("[${Thread.currentThread().name}] starting $id")
    Thread.sleep(1000)
    println("[${Thread.currentThread().name}] finishing $id")
}