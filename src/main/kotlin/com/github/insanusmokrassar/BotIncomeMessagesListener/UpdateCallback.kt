package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.github.insanusmokrassar.IObjectK.interfaces.IObject

/**
 * @param T
 */
interface UpdateCallback<T> : (Int, IObject<Any>, T) -> Unit