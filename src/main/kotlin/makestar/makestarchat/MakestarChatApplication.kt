package makestar.makestarchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MakestarChatApplication

fun main(args: Array<String>) {
    runApplication<MakestarChatApplication>(*args)
}
