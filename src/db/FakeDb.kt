package db

import kotlinx.coroutines.delay
import java.time.Instant

class FakeDb {
    private val DELAY = 200L
    private val kvs = HashMap<String, String>()
    suspend fun set(k: String, v: String) {
        delay(DELAY)
        kvs[k] = v
        println("[${Instant.now()}] set $k = $v")
    }
    suspend fun get(k: String): String? {
        delay(DELAY)
        println("[${Instant.now()}] fetched ${kvs[k]}")
        return kvs[k]
    }
}

