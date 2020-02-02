import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

sealed class RingMessage()
data class SetNeighbor(val neighbor: SendChannel<RingMessage>) : RingMessage()
data class Counter(val value: Int) : RingMessage()
object Kill : RingMessage()

fun main() = runBlocking{
    val actors = List(100) { i ->
        actor<RingMessage> {
            var neighbor: SendChannel<RingMessage>? = null
            for (msg in channel) {
                when (msg) {
                    is SetNeighbor -> neighbor = msg.neighbor
                    is Counter -> {
                        println("$i --- ${msg.value}")
                        if (msg.value > 0) {
                            neighbor?.send(Counter(msg.value - 1))
                        } else {
                            neighbor?.send(Kill)
                        }
                    }
                    is Kill -> {
                        channel.close()
                        if (neighbor?.isClosedForSend == false) {
                            neighbor.offer(Kill)
                        }
                    }
                }
            }
        }
    }

    for (i in 0..99) {
        actors[i].send(SetNeighbor(actors[(i+1)%100]))
    }

    actors[0].send(Counter(1_000_000))
}