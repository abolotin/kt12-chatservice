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
        try {
            val message = messages.first { it.id == messageId }
            messages.remove(message)
        } catch (e: NoSuchElementException) {
            throw NoMessageException(messageId)
        }
    }

    fun hasUnreadMessages(): Boolean = try {
        messages.first { !it.isReaded }
        true
    } catch (e: NoSuchElementException) {
        false
    }
}
