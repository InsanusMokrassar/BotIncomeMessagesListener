package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.CoroutineScope

interface UpdatesHandlerPreparator : (MutableList<Update>) -> (suspend CoroutineScope.() -> Unit)

