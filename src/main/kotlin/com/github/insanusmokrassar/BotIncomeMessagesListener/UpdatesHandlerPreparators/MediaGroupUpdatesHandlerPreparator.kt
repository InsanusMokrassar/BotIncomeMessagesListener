package com.github.insanusmokrassar.BotIncomeMessagesListener.UpdatesHandlerPreparators

import com.github.insanusmokrassar.BotIncomeMessagesListener.MediaGroupCallback
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.CoroutineScope

internal open class MediaGroupUpdatesHandlerPreparator (
    private val callback: MediaGroupCallback,
    private val predicate: (Update) -> Pair<String, Message>?
) : UpdatesHandlerPreparator {
    override fun invoke(updates: MutableList<Update>): suspend CoroutineScope.() -> Unit {
        val mediaGroups = mutableMapOf<String, MutableList<Message>>()
        updates.removeAll {
            predicate(it) ?.also {
                pair ->
                (mediaGroups[pair.first] ?: mutableListOf<Message>().also {
                    mediaGroups[pair.first] = it
                }).add(
                    pair.second
                )
            } != null
        }
        return {
            mediaGroups.forEach {
                (k, v) ->
                callback(k, v)
            }
        }
    }
}

internal class MessageMediaGroupUpdatesHandlerPreparator(
    callback: MediaGroupCallback
) : MediaGroupUpdatesHandlerPreparator(
    callback,
    {
        it.message() ?.let {
            message ->
            message.mediaGroupId() ?.let {
                it to message
            }
        }
    }
)

internal class MessageEditedMediaGroupUpdatesHandlerPreparator(
    callback: MediaGroupCallback
) : MediaGroupUpdatesHandlerPreparator(
    callback,
    {
        it.editedMessage() ?.let {
            message ->
            message.mediaGroupId() ?.let {
                it to message
            }
        }
    }
)

internal class ChannelPostMediaGroupUpdatesHandlerPreparator(
    callback: MediaGroupCallback
) : MediaGroupUpdatesHandlerPreparator(
    callback,
    {
        it.channelPost() ?.let {
            message ->
            message.mediaGroupId() ?.let {
                it to message
            }
        }
    }
)

internal class ChannelPostEditedMediaGroupUpdatesHandlerPreparator(
    callback: MediaGroupCallback
) : MediaGroupUpdatesHandlerPreparator(
    callback,
    {
        it.editedChannelPost() ?.let {
            message ->
            message.mediaGroupId() ?.let {
                it to message
            }
        }
    }
)
