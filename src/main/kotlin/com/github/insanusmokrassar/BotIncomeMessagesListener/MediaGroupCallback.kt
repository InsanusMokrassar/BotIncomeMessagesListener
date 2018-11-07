package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.pengrad.telegrambot.model.Message
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

typealias MediaGroupCallback = (String, List<Message>) -> Unit

@ExperimentalCoroutinesApi
class MediaGroupCallbackChannel(
    private val scope: CoroutineScope = GlobalScope
) : MediaGroupCallback {
    val broadcastChannel = BroadcastChannel<Pair<String, List<Message>>>(
        Channel.CONFLATED
    )

    override fun invoke(p1: String, p2: List<Message>) {
        scope.launch {
            broadcastChannel.send(p1 to p2)
        }
    }
}
