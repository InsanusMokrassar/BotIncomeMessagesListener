package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.pengrad.telegrambot.model.Message
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.launch

typealias MediaGroupCallback = (String, List<Message>) -> Unit

private const val broadcastSubscribersCount = 256

class MediaGroupCallbackChannel : MediaGroupCallback {
    val broadcastChannel = BroadcastChannel<Pair<String, List<Message>>>(
        broadcastSubscribersCount
    )

    override fun invoke(p1: String, p2: List<Message>) {
        launch {
            broadcastChannel.send(p1 to p2)
        }
    }
}
