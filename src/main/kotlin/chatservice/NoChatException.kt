package chatservice

class NoChatException(userId: Int) : RuntimeException("Specified chat does not found: userId=$userId")