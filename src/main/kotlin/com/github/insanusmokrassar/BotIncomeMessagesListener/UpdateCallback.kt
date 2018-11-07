package com.github.insanusmokrassar.BotIncomeMessagesListener

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

/**
 * @param T
 */
typealias UpdateCallback<T> = (Int, T) -> Unit

@ExperimentalCoroutinesApi
class UpdateCallbackChannel<T>(
    private val scope: CoroutineScope = GlobalScope
) : UpdateCallback<T> {
    val broadcastChannel = BroadcastChannel<Pair<Int, T>>(
        Channel.CONFLATED
    )

    override fun invoke(p1: Int, p2: T) {
        scope.launch {
            broadcastChannel.send(p1 to p2)
        }
    }
}
