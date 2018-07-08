package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.*
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.runBlocking

class BotIncomeMessagesListenerChannels(
    onMessageChannel: SendChannel<Pair<Int, Message>>? = null,
    onMessageEditedChannel: SendChannel<Pair<Int, Message>>? = null,
    onChannelPostChannel: SendChannel<Pair<Int, Message>>? = null,
    onChannelPostEditedChannel: SendChannel<Pair<Int, Message>>? = null,
    onInlineQueryChannel: SendChannel<Pair<Int, InlineQuery>>? = null,
    onChosenInlineResultChannel: SendChannel<Pair<Int, ChosenInlineResult>>? = null,
    onCallbackQueryChannel: SendChannel<Pair<Int, CallbackQuery>>? = null,
    onShippingQueryChannel: SendChannel<Pair<Int, ShippingQuery>>? = null,
    onPreCheckoutQueryChannel: SendChannel<Pair<Int, PreCheckoutQuery>>? = null,
    onMessageMediaGroupChannel: SendChannel<Pair<String, List<Message>>>? = null,
    onMessageEditedMediaGroupChannel: SendChannel<Pair<String, List<Message>>>? = null,
    onChannelPostMediaGroupChannel: SendChannel<Pair<String, List<Message>>>? = null,
    onChannelPostEditedMediaGroupChannel: SendChannel<Pair<String, List<Message>>>? = null,
    bot: TelegramBot? = null
) : UpdatesListener by BotIncomeMessagesListener(
    onMessageChannel ?.let {
        {
            updateId: Int, message: Message ->
            runBlocking {
                it.send(updateId to message)
            }
        } as UpdateCallback<Message>
    },
    onMessageEditedChannel ?.let {
        {
            updateId: Int, message: Message ->
            runBlocking {
                it.send(updateId to message)
            }
        } as UpdateCallback<Message>
    },
    onChannelPostChannel ?.let {
        {
            updateId: Int, message: Message ->
            runBlocking {
                it.send(updateId to message)
            }
        } as UpdateCallback<Message>
    },
    onChannelPostEditedChannel ?.let {
        {
            updateId: Int, message: Message ->
            runBlocking {
                it.send(updateId to message)
            }
        } as UpdateCallback<Message>
    },
    onInlineQueryChannel ?.let {
        {
            updateId: Int, query: InlineQuery ->
            runBlocking {
                it.send(updateId to query)
            }
        } as UpdateCallback<InlineQuery>
    },
    onChosenInlineResultChannel ?.let {
        {
            updateId: Int, result: ChosenInlineResult ->
            runBlocking {
                it.send(updateId to result)
            }
        } as UpdateCallback<ChosenInlineResult>
    },
    onCallbackQueryChannel ?.let {
        {
            updateId: Int, query: CallbackQuery ->
            runBlocking {
                it.send(updateId to query)
            }
        } as UpdateCallback<CallbackQuery>
    },
    onShippingQueryChannel ?.let {
        {
            updateId: Int, query: ShippingQuery ->
            runBlocking {
                it.send(updateId to query)
            }
        } as UpdateCallback<ShippingQuery>
    },
    onPreCheckoutQueryChannel ?.let {
        {
            updateId: Int, query: PreCheckoutQuery ->
            runBlocking {
                it.send(updateId to query)
            }
        } as UpdateCallback<PreCheckoutQuery>
    },
    onMessageMediaGroupChannel ?.let {
        {
            mediaGroupId: String, messages: List<Message> ->
            runBlocking {
                it.send(mediaGroupId to messages)
            }
        } as MediaGroupCallback
    },
    onMessageEditedMediaGroupChannel ?.let {
        {
            mediaGroupId: String, messages: List<Message> ->
            runBlocking {
                it.send(mediaGroupId to messages)
            }
        } as MediaGroupCallback
    },
    onChannelPostMediaGroupChannel ?.let {
        {
            mediaGroupId: String, messages: List<Message> ->
            runBlocking {
                it.send(mediaGroupId to messages)
            }
        } as MediaGroupCallback
    },
    onChannelPostEditedMediaGroupChannel ?.let {
        {
            mediaGroupId: String, messages: List<Message> ->
            runBlocking {
                it.send(mediaGroupId to messages)
            }
        } as MediaGroupCallback
    },
    bot
)
