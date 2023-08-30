package FurryLoaderMC.TelegramBot

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    companion object {

        lateinit var instance: JavaPlugin
        lateinit var webSocket: WebSocket


        fun sendMessageToGame(
            telegram: String,
            minecraft: String,
            message: String,
            telegram_id: Long,
            message_id: Long
        ) {
            val content = Utils.furryLoaderMessage(telegram, minecraft, message, telegram_id, message_id)
            this.instance.server.sendMessage(content)
        }


        fun sendMessageToBot(message: String) {
            this.webSocket.sendMessage(message)
        }


        fun outputLoggerInfo(content: String) {
            this.instance.logger.info(content)
        }

    }


    override fun onLoad() {
        instance = this
        webSocket = WebSocket()
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
