package makestar.makestarchat.application

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import makestar.makestarchat.domain.ChatMessage
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class RedisSubscriber(
    private val service: ChatMessageService,
    private val simpMessagingTemplate: SimpMessagingTemplate
) : MessageListener {
    private val objectMapper = jacksonObjectMapper()
    override fun onMessage(message: Message, pattern: ByteArray?) {
        val messageBody = String(message.body)
        val chatMessage: ChatMessage = objectMapper.readValue(messageBody)
        service.saveChatMessage(chatMessage)
        simpMessagingTemplate.convertAndSend("/topic/messages", messageBody)
    }
}
