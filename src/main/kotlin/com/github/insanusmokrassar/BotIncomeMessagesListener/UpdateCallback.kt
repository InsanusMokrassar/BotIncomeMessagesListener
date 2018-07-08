package com.github.insanusmokrassar.BotIncomeMessagesListener

/**
 * @param T
 */
interface UpdateCallback<T> : (Int, T) -> Unit
