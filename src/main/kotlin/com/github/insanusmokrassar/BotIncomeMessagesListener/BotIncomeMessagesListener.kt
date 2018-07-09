package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.github.insanusmokrassar.BotIncomeMessagesListener.UpdatesHandlerPreparators.*
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.*
import kotlinx.coroutines.experimental.*
import java.util.logging.Logger

private val logger = Logger.getLogger(BotIncomeMessagesListener::class.java.simpleName)

class BotIncomeMessagesListener(
    onMessage: UpdateCallback<Message>? = null,
    onMessageEdited: UpdateCallback<Message>? = null,
    onChannelPost: UpdateCallback<Message>? = null,
    onChannelPostEdited: UpdateCallback<Message>? = null,
    onInlineQuery: UpdateCallback<InlineQuery>? = null,
    onChosenInlineResult: UpdateCallback<ChosenInlineResult>? = null,
    onCallbackQuery: UpdateCallback<CallbackQuery>? = null,
    onShippingQuery: UpdateCallback<ShippingQuery>? = null,
    onPreCheckoutQuery: UpdateCallback<PreCheckoutQuery>? = null,
    onMessageMediaGroup: MediaGroupCallback? = null,
    onMessageEditedMediaGroup: MediaGroupCallback? = null,
    onChannelPostMediaGroup: MediaGroupCallback? = null,
    onChannelPostEditedMediaGroup: MediaGroupCallback? = null,
    bot: TelegramBot? = null
) : UpdatesListener {
    private val handlerPreparators: List<UpdatesHandlerPreparator> = ArrayList<UpdatesHandlerPreparator>().also {
        list ->
        onMessageMediaGroup ?.let {
            list.add(MessageMediaGroupUpdatesHandlerPreparator(it))
        }
        onMessageEditedMediaGroup ?.let {
            list.add(MessageEditedMediaGroupUpdatesHandlerPreparator(it))
        }

        onChannelPostMediaGroup ?.let {
            list.add(ChannelPostMediaGroupUpdatesHandlerPreparator(it))
        }

        onChannelPostEditedMediaGroup ?.let {
            list.add(ChannelPostEditedMediaGroupUpdatesHandlerPreparator(it))
        }

        onMessage ?. let {
            list.add(OnMessageUpdatesHandlerPreparator(it))
        }
        onMessageEdited ?. let {
            list.add(OnMessageEditUpdatesHandlerPreparator(it))
        }
        onChannelPost ?. let {
            list.add(OnChannelPostUpdatesHandlerPreparator(it))
        }
        onChannelPostEdited ?. let {
            list.add(OnChannelPostEditUpdatesHandlerPreparator(it))
        }

        onInlineQuery ?.let {
            list.add(OnInlineQueryUpdatesHandlerPreparator(it))
        }
        onChosenInlineResult ?.let {
            list.add(OnChosenInlineResultUpdatesHandlerPreparator(it))
        }

        onCallbackQuery ?.let {
            list.add(OnCallbackQueryUpdatesHandlerPreparator(it))
        }

        onShippingQuery ?.let {
            list.add(OnShippingQueryUpdatesHandlerPreparator(it))
        }

        onPreCheckoutQuery ?.let {
            list.add(OnPreCheckoutQueryUpdatesHandlerPreparator(it))
        }

        logger.info("Added handlers: ${list.joinToString { it::class.java.simpleName }}")
    }

    init {
        bot ?. setUpdatesListener(this)
    }

    override fun process(updates: MutableList<Update>?): Int {
        return updates ?.let {
            ArrayList(it).let {
                updates ->
                runBlocking {
                    val jobs = ArrayList<Job>()
                    jobs.addAll(
                        handlerPreparators.map {
                            it(updates)
                        }.map {
                            callback ->
                            launch(start = CoroutineStart.LAZY) {
                                try {
                                    callback()
                                } catch (e: Throwable) {
                                    jobs.forEach {
                                        it.cancel(e)
                                    }
                                    throw e
                                }
                            }
                        }
                    )
                    jobs.forEach {
                        it.start()
                    }
                    jobs.firstOrNull {
                        try {
                            it.join()
                            false
                        } catch (e: Exception) {
                            true
                        }
                    } ?. let {
                        UpdatesListener.CONFIRMED_UPDATES_NONE
                    }
                }
            }
        } ?: UpdatesListener.CONFIRMED_UPDATES_ALL
    }
}
