package chatservice

class NoMessageException(messageId: Int) : RuntimeException("Specified message does not found: messageId=$messageId")