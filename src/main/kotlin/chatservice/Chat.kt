package chatservice

data class Chat(
    val id: Int,
    var messages: MutableList<Message> = mutableListOf()
) {
    var messageCounter = 0

    fun addMessage(text: String): Int {
        messages += Message(id = ++messageCounter, userId = id, text)
        return messageCounter
    }

    fun deleteMessage(messageId: Int) {
        messages.remove(
            messages.firstOrNull { it.id == messageId } ?: throw NoMessageException(messageId)
        )
    }

    fun hasUnreadMessages(): Boolean = messages.any { !it.isReaded }
}
