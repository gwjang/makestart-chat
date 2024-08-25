package makestar.makestarchat.infra

import makestar.makestarchat.domain.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface IChatMessageRepository : JpaRepository<ChatMessage, Long>
