package makestar.makestarchat.config

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Service

@Service
class RedisSubscriber : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        // Redis에서 수신한 메시지 처리 로직
        val messageBody = String(message.body)
        println("Received message: $messageBody")
        // 추가적인 처리 로직을 여기에 작성합니다.
    }
}
