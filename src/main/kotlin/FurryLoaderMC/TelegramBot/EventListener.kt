package FurryLoaderMC.TelegramBot

import FurryLoaderMC.TelegramBot.Main.Companion.webSocket
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.entity.PlayerDeathEvent
import io.papermc.paper.event.player.AsyncChatEvent
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


@Serializable
data class Data(
    val type: String,
    val name: String,
    val content: String,
)


class EventListener: Listener {

    @EventHandler fun onPlayerJoin(event: PlayerJoinEvent) {
        val type = "join"
        val name = event.player.name
        val message = Utils.componentToString(event.joinMessage() ?: return)
        webSocket.send(Json.encodeToString((Data(type, name, message))))
    }


    @EventHandler fun onPlayerQuit(event: PlayerQuitEvent) {
        val type = "quit"
        val name = event.player.name
        val message = Utils.componentToString(event.quitMessage() ?: return)
        webSocket.send(Json.encodeToString((Data(type, name, message))))
    }


    @EventHandler fun onPlayerDeath(event: PlayerDeathEvent) {
        val type = "death"
        val name = event.player.name
        val message = Utils.componentToString(event.deathMessage() ?: return)
        webSocket.send(Json.encodeToString((Data(type, name, message))))
    }


    @EventHandler fun onPlayerChat(event: AsyncChatEvent) {
        val type = "chat"
        val name = event.player.name
        val message = Utils.componentToString(event.originalMessage())
        webSocket.send(Json.encodeToString((Data(type, name, message))))
    }


    @EventHandler fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        val type = "advancement"
        val name = event.player.name
        val message = Utils.componentToString(event.message() ?: return)
        webSocket.send(Json.encodeToString((Data(type, name, message))))
    }

}
