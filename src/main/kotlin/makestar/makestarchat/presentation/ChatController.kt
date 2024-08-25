package makestar.makestarchat.presentation

import makestar.makestarchat.application.RedisPublisher
import makestar.makestarchat.domain.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatController(
    private val redisPublisher: RedisPublisher
) {
    private val maxMessageLength = 1000
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    fun sendMessage(@Payload chatMessage: ChatMessage) {
        validateMessage(chatMessage)
        redisPublisher.publish(chatMessage)
    }

    private fun validateMessage(chatMessage: ChatMessage) {
        if (chatMessage.content.length > maxMessageLength) {
            throw IllegalArgumentException("Message is too long")
        }
    }
}
