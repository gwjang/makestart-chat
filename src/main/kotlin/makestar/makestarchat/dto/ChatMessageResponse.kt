package makestar.makestarchat.dto

data class ChatMessageResponse(
    var sender: String,
    var content: String,
    var type: MessageType
) {
    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
