package FurryLoaderMC.TelegramBot

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class WebSocket {

    private val botAction = BotAction()
    private val sessions = mutableListOf<DefaultWebSocketSession>()
    private val server = embeddedServer(Netty, port = 4321) {
        install(WebSockets)
        install(Routing) {
            webSocket {
                onConnect()
                onMessage()
                onClose()
            }
        }
    }


    private fun DefaultWebSocketSession.onConnect() {
        this@WebSocket.sessions.add(this)
        Main.outputLoggerInfo("Telegram Bot 已连接 WebSocket 服务器")
    }


    private suspend fun DefaultWebSocketSession.onClose() {
        this.closeReason.await()
        this@WebSocket.sessions.remove(this)
        Main.outputLoggerInfo("Telegram Bot 已断开 WebSocket 服务器")
    }


    private suspend fun DefaultWebSocketSession.onMessage() {
        this.incoming.consumeEach {
            if (it is Frame.Text) {
                this@WebSocket.botAction.onMessage(it.readText())
            }
        }
    }


    fun sendMessage(content: String) = runBlocking {
        launch {
            this@WebSocket.sessions.forEach {
                it.send(content)
            }
        }
    }


    fun start() {
        this.server.start()
    }


    fun stop() {
        this.server.stop()
    }

}
