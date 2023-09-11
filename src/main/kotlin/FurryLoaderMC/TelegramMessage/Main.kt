package FurryLoaderMC.TelegramMessage

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.configuration.file.FileConfiguration
import FurryLoaderMC.TelegramMessage.Command.*
import FurryLoaderMC.TelegramMessage.Listener.*


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
        this.server.pluginManager.registerEvents(PlayerJoinListener(), this)
        this.server.pluginManager.registerEvents(PlayerQuitListener(), this)
        this.server.pluginManager.registerEvents(PlayerDeathListener(), this)
        this.server.pluginManager.registerEvents(PlayerAdvancementDoneListener(), this)
        this.server.pluginManager.registerEvents(AsyncChatEventListener(), this)
        socketIO.connect()
    }


    override fun onDisable() {
        socketIO.disconnect()
    }

}
