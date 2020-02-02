import kotlinx.coroutines.flow.*
import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture

fun main() {
    class Remote(n: Int) {
        private val strings = (1 .. n).map { it.toString() }.toList().sorted()
        fun list(startAfter: String?): CompletableFuture<List<String>> {
            val vs = if (startAfter.isNullOrEmpty()) strings else strings.dropWhile { it <= startAfter }
            return CompletableFuture.completedFuture(vs.take(20))
        }
    }

    fun naive(r: Remote): List<String> {
        val all = arrayListOf<String>()
        var pt = ""
        do {
            val page = r.list(pt).get()
            all.addAll(page)
            pt = page.lastOrNull() ?: ""
        } while (pt.isNotEmpty())
        return all
    }

    suspend fun smart(r: Remote): Flow<String> {
        var pt = ""
        val pages = flow {
            do {
                val page = r.list(pt).await()
                emit(page)
                pt = page.lastOrNull()?:""
            } while (pt.isNotEmpty())
        }
        return pages.flatMapConcat { it.asFlow() }
    }

    val r = Remote(1_000)
    println(naive(r).map { it.length }.sum())
    println(runBlocking {
        smart(r).fold(0, { acc, s -> acc + s.length })
    })
}