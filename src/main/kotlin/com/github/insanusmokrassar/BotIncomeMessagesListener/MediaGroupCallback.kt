package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.pengrad.telegrambot.model.Message
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch

typealias MediaGroupCallback = (String, List<Message>) -> Unit

class MediaGroupCallbackChannel : MediaGroupCallback {
    val broadcastChannel = BroadcastChannel<Pair<String, List<Message>>>(
        Channel.CONFLATED
    )

    override fun invoke(p1: String, p2: List<Message>) {
        launch {
            broadcastChannel.send(p1 to p2)
        }
    }
}
