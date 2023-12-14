package chatservice

object ChatService {
    private var chats: MutableMap<Int, Chat> = mutableMapOf()

    fun clear() {
        chats.clear();
    }

    fun addMessage(userId: Int, text: String) =
        (chats[userId] ?: Chat(id = userId)).let {
            chats[userId] = it
            it.addMessage(text)
        }

    fun getChats() = chats.values

    fun getLastMessages() = chats.values.map {
        try {
            it.messages.last()
        } catch(e : NoSuchElementException) {
        }
    }

    fun getMessages(userId: Int, count: Int? = null) =
        (count ?: chats[userId]?.messages?.size)?.let { lastIndex ->
            chats[userId]?.messages?.subList(0, lastIndex)?.onEach { message ->
                message.isReaded = true
            }
        } ?: throw NoMessagesException()

    fun deleteMessage(userId: Int, messageId: Int) =
        (chats[userId] ?: throw NoChatException(userId)).deleteMessage(messageId)

    fun deleteChat(userId: Int) = chats.remove(userId) ?: throw NoChatException(userId)

    fun getUnreadChatsCount(): Int = chats
        .filter { it.value.hasUnreadMessages() }
        .count()
}
