package makestar.makestarchat.application

import makestar.makestarchat.domain.ChatMessage
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

@Service
class RedisPublisher(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val topic: ChannelTopic
) {

    fun publish(message: ChatMessage) {
        redisTemplate.convertAndSend(topic.topic, message)
    }
}
