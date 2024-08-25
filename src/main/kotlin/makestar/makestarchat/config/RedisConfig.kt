import makestar.makestarchat.config.RedisSubscriber
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@EnableCaching
@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val clusterNodes: List<String>,
    @Value("\${spring.data.redis.password}")
    private val redisPassword: String,
    private val redisSubscriber: RedisSubscriber
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisClusterConfiguration = RedisClusterConfiguration(clusterNodes)
        redisClusterConfiguration.setPassword(redisPassword)
        return LettuceConnectionFactory(redisClusterConfiguration)
    }

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            setConnectionFactory(connectionFactory)
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer()
        }
    }

    @Bean
    fun container(connectionFactory: RedisConnectionFactory, listenerAdapter: MessageListenerAdapter): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            setConnectionFactory(connectionFactory)
            addMessageListener(listenerAdapter, topic())
        }
    }

    @Bean
    fun listenerAdapter(): MessageListenerAdapter {
        return MessageListenerAdapter(this.redisSubscriber, "onMessage")
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("chatroom")
    }
}
