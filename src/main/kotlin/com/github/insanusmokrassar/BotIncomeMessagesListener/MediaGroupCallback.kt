package com.github.insanusmokrassar.BotIncomeMessagesListener

import com.pengrad.telegrambot.model.Message

interface MediaGroupCallback : (String, List<Message>) -> Unit