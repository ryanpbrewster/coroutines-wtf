package db

import kotlinx.coroutines.delay
import java.util.concurrent.Future

class DbPollWrapper(private val underlying: FutureDb) {
    suspend fun get(k: String): String? {
        return poll(underlying.get(k))
    }
    suspend fun set(k: String, v: String): Unit {
        return poll(underlying.set(k, v))
    }

    private suspend fun <T> poll(fut: Future<T>): T {
        while (true) {
            if (fut.isDone) {
                return fut.get()
            }
            delay(50)
        }
    }
}
