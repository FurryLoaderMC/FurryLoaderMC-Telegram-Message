package FurryLoaderMC.TelegramBot

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    companion object {
        lateinit var instance: JavaPlugin
        lateinit var webSocket: WebSocket
    }


    override fun onEnable() {
        instance = this
        webSocket = WebSocket()
        webSocket.start()
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
    }


    override fun onDisable() {
        webSocket.stop()
    }

}
