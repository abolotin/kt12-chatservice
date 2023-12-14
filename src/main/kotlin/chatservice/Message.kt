package chatservice

data class Message(
    val id: Int,
    val userId: Int,
    var text: String,
    var isReaded: Boolean = false
)