package com.github.insanusmokrassar.BotIncomeMessagesListener

import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch

/**
 * @param T
 */
typealias UpdateCallback<T> = (Int, T) -> Unit

class UpdateCallbackChannel<T> : UpdateCallback<T> {
    val broadcastChannel = BroadcastChannel<Pair<Int, T>>(
        Channel.CONFLATED
    )

    override fun invoke(p1: Int, p2: T) {
        launch {
            broadcastChannel.send(p1 to p2)
        }
    }
}
