package com.github.insanusmokrassar.BotIncomeMessagesListener.UpdatesHandlers

import com.github.insanusmokrassar.BotIncomeMessagesListener.UpdateCallback
import com.github.insanusmokrassar.BotIncomeMessagesListener.UpdatesHandlerPreparator
import com.github.insanusmokrassar.IObjectKRealisations.toIObject
import com.pengrad.telegrambot.model.*
import kotlinx.coroutines.experimental.CoroutineScope

internal open class StandardUpdatesHandlerPreparator<T> (
    private val callback: UpdateCallback<T>,
    private val predicate: (Update) -> T?
) : UpdatesHandlerPreparator {
    override fun invoke(updates: MutableList<Update>): suspend CoroutineScope.() -> Unit {
        val correctUpdates = mutableListOf<Pair<Update, T>>()
        updates.removeAll {
            predicate(it) ?.also {
                updateObject ->
                correctUpdates.add(it to updateObject)
            } != null
        }
        return {
            correctUpdates.forEach {
                callback(it.first.updateId(), it.first.toIObject(), it.second)
            }
        }
    }
}

internal class OnMessageUpdatesHandlerPreparator(
    callback: UpdateCallback<Message>
) : StandardUpdatesHandlerPreparator<Message>(
    callback,
    {
        it.message()
    }
)

internal class OnMessageEditUpdatesHandlerPreparator(
    callback: UpdateCallback<Message>
) : StandardUpdatesHandlerPreparator<Message>(
    callback,
    {
        it.editedMessage()
    }
)

internal class OnChannelPostUpdatesHandlerPreparator(
    callback: UpdateCallback<Message>
) : StandardUpdatesHandlerPreparator<Message>(
    callback,
    {
        it.channelPost()
    }
)

internal class OnChannelPostEditUpdatesHandlerPreparator(
    callback: UpdateCallback<Message>
) : StandardUpdatesHandlerPreparator<Message>(
    callback,
    {
        it.editedChannelPost()
    }
)

internal class OnInlineQueryUpdatesHandlerPreparator(
    callback: UpdateCallback<InlineQuery>
) : StandardUpdatesHandlerPreparator<InlineQuery>(
    callback,
    {
        it.inlineQuery()
    }
)

internal class OnChosenInlineResultUpdatesHandlerPreparator(
    callback: UpdateCallback<ChosenInlineResult>
) : StandardUpdatesHandlerPreparator<ChosenInlineResult>(
    callback,
    {
        it.chosenInlineResult()
    }
)

internal class OnCallbackQueryUpdatesHandlerPreparator(
    callback: UpdateCallback<CallbackQuery>
) : StandardUpdatesHandlerPreparator<CallbackQuery>(
    callback,
    {
        it.callbackQuery()
    }
)

internal class OnShippingQueryUpdatesHandlerPreparator(
    callback: UpdateCallback<ShippingQuery>
) : StandardUpdatesHandlerPreparator<ShippingQuery>(
    callback,
    {
        it.shippingQuery()
    }
)

internal class OnPreCheckoutQueryUpdatesHandlerPreparator(
    callback: UpdateCallback<PreCheckoutQuery>
) : StandardUpdatesHandlerPreparator<PreCheckoutQuery>(
    callback,
    {
        it.preCheckoutQuery()
    }
)
