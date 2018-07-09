package com.github.insanusmokrassar.BotIncomeMessagesListener

import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.launch

/**
 * @param T
 */
typealias UpdateCallback<T> = (Int, T) -> Unit

private const val broadcastSubscribersCount = 256

class UpdateCallbackChannel<T> : UpdateCallback<T> {
    val broadcastChannel = BroadcastChannel<Pair<Int, T>>(
        broadcastSubscribersCount
    )

    override fun invoke(p1: Int, p2: T) {
        launch {
            broadcastChannel.send(p1 to p2)
        }
    }
}
