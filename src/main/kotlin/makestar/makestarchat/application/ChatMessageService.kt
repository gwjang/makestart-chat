package makestar.makestarchat.application

import makestar.makestarchat.domain.ChatMessage
import makestar.makestarchat.infra.IChatMessageRepository
import org.springframework.stereotype.Service

@Service
class ChatMessageService (
    private val repository: IChatMessageRepository
) {
    fun saveChatMessage(chatMessage: ChatMessage) {
        try {
            val entity = ChatMessage(sender = chatMessage.sender, content = chatMessage.content, timestamp = chatMessage.timestamp)
            repository.save(entity)
        } catch (e: Exception) {
            println("Failed to save chat message: ${e.message}")
        }
    }
}
