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

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    fun sendMessage(@Payload chatMessage: ChatMessage) {
        redisPublisher.publish(chatMessage)
    }
}
