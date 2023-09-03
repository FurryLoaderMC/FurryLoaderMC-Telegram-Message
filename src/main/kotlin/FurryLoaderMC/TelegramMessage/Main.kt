package FurryLoaderMC.TelegramMessage

import FurryLoaderMC.TelegramMessage.Command.Executor
import FurryLoaderMC.TelegramMessage.Command.Completer
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    companion object {

        lateinit var pluginConfig: FileConfiguration
        lateinit var instance: JavaPlugin
        lateinit var socketIO: SocketIO
        lateinit var furryLoaderMessage: FurryLoaderMessage


        fun sendMessageToGame(data: Data) {
            this.furryLoaderMessage.sendMessage(data)
        }


        fun sendMessageToBot(event: String, data: Data) {
            this.socketIO.sendMessage(event, data)
        }


        fun outputLoggerInfo(content: String) {
            this.instance.logger.info(content)
        }

    }


    override fun onLoad() {
        pluginConfig = config
        instance = this
        socketIO = SocketIO()
        furryLoaderMessage = FurryLoaderMessage()
    }


    override fun onEnable() {
        saveDefaultConfig()
        getCommand("telegram")?.setExecutor(Executor())
        getCommand("telegram")?.tabCompleter = Completer()
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
        socketIO.connect()
    }


    override fun onDisable() {
        socketIO.disconnect()
    }

}
