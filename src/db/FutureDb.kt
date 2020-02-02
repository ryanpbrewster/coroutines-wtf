package db

import java.time.Instant
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import kotlin.collections.HashMap

class FutureDb {
    private val DELAY = 200L
    private val timer = Timer()
    private val kvs = HashMap<String, String>()
    fun set(k: String, v: String): Future<Unit> {
        val fut = CompletableFuture<Unit>()
        timer.schedule(object : TimerTask() {
            override fun run() {
                kvs[k] = v
                println("[${Instant.now()}] set $k = $v")
                fut.complete(Unit)
            }
        }, DELAY)
        return fut
    }
    fun get(k: String): Future<String?> {
        val fut = CompletableFuture<String?>()
        timer.schedule(object : TimerTask() {
            override fun run() {
                println("[${Instant.now()}] fetched ${kvs[k]}")
                fut.complete(kvs[k])
            }
        }, DELAY)
        return fut
    }
}
