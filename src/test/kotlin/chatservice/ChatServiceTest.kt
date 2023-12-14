package chatservice

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChatServiceTest {

    @Before
    fun clearChatService() = ChatService.clear()

    @Test
    fun addMessageOk() {
        assertNotEquals(0, ChatService.addMessage(1, "Message"))
    }

    @Test
    fun getChatsEmpty() {
        assertEquals(0, ChatService.getChats().size)
    }

    @Test
    fun getChatsExists() {
        ChatService.addMessage(1, "Message")
        assertNotEquals(0, ChatService.getChats().size)
    }

    @Test
    fun getLastMessagesEmpty() {
        assertEquals(0, ChatService.getLastMessages().size)
    }

    @Test
    fun getLastMessagesExists() {
        ChatService.addMessage(1, "Message")
        assertNotEquals(0, ChatService.getLastMessages().size)
    }

    @Test
    fun getMessagesOk() {
        ChatService.addMessage(1, "Message")
        assertNotNull(ChatService.getMessages(1))
    }

    @Test
    fun getMessagesSlice() {
        ChatService.addMessage(1, "Message 1")
        ChatService.addMessage(1, "Message 2")
        assertEquals(1, ChatService.getMessages(1, 1).size)
    }

    @Test (expected = NoMessagesException::class)
    fun getMessagesFail() {
        assertNotNull(ChatService.getMessages(1))
    }

    @Test
    fun deleteMessageOk() {
        val messageId = ChatService.addMessage(1, "Message")
        assertNotNull(ChatService.deleteMessage(1, messageId))
    }

    @Test (expected = NoMessageException::class)
    fun deleteMessageFailMessage() {
        val messageId = ChatService.addMessage(1, "Message")
        assertNotNull(ChatService.deleteMessage(1, messageId+1000))
    }

    @Test (expected = NoChatException::class)
    fun deleteMessageFailChat() {
        val messageId = ChatService.addMessage(1, "Message")
        assertNotNull(ChatService.deleteMessage(1000, messageId))
    }

    @Test
    fun deleteChatOk() {
        ChatService.addMessage(1, "Message")
        assertNotNull(ChatService.deleteChat(1))
    }

    @Test (expected = NoChatException::class)
    fun deleteChatFail() {
        ChatService.addMessage(1, "Message")
        assertNotNull(ChatService.deleteChat(1000))
    }

    @Test
    fun getUnreadChatsCountZero() {
        assertEquals(0, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getUnreadChatsCountExists() {
        ChatService.addMessage(1, "Message")
        assertEquals(1, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getUnreadChatsCountReaded() {
        ChatService.addMessage(1, "Message")
        ChatService.getMessages(1)
        assertEquals(0, ChatService.getUnreadChatsCount())
    }
}