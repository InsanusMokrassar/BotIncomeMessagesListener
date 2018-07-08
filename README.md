# BotIncomeMessagesListener

It is simple library which provide access to telegram bot
updates events in separated mode

## What is supported now?

Now supported separated events for:

* New message
* Edited message
* New channel post
* Edited channel post
* Inline query
* Chosen inline result
* Callback query
* Shipping query
* Pre checkout query

Also all events which generate [Message](https://core.telegram.org/bots/api#message) entity
will be wrapped into media group call back such as:

* New messages media group
* Edited messages media group (rare or impossible)
* New channel posts media group
* Edited channel posts media group (rare or impossible)

## How to import?

### Maven

```xml
<dependency>
    <groupId>com.github.insanusmokrassar</groupId>
    <artifactId>BotIncomeMessagesListener</artifactId>
    <version>0.8b</version>
</dependency>
```

### Gradle

```
implementation 'com.github.insanusmokrassar:BotIncomeMessagesListener:0.8b'
```

### Old Gradle

```
compile 'com.github.insanusmokrassar:BotIncomeMessagesListener:0.8b'
```
