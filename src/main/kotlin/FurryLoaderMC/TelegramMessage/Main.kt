package FurryLoaderMC.TelegramMessage

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    companion object {

        lateinit var instance: JavaPlugin
        lateinit var webSocket: WebSocket
        lateinit var furryLoaderMessage: FurryLoaderMessage


        fun sendMessageToGame(sender: Sender, message: Message) {
            this.furryLoaderMessage.sendMessage(sender, message)
        }


        fun sendMessageToBot(data: Data) {
            this.webSocket.sendMessage(data)
        }


        fun outputLoggerInfo(content: String) {
            this.instance.logger.info(content)
        }

    }


    override fun onLoad() {
        instance = this
        webSocket = WebSocket()
        furryLoaderMessage = FurryLoaderMessage()
    }


    override fun onEnable() {
        webSocket.start()
        getCommand("telegram")?.setExecutor(CommandExecutor())
        getCommand("telegram")?.tabCompleter = TabCompleter()
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
    }


    override fun onDisable() {
        webSocket.stop()
    }

}
