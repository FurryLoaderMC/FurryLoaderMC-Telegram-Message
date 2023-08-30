package FurryLoaderMC.TelegramMessage

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.entity.PlayerDeathEvent
import io.papermc.paper.event.player.AsyncChatEvent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class EventListener: Listener {

    @EventHandler fun onPlayerJoin(event: PlayerJoinEvent) {
        val sendData = SendData(
            "message",
            "join",
            Message(
                "send",
                Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                ),
                Utils.componentToString(event.joinMessage() ?: return),
                null
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }


    @EventHandler fun onPlayerQuit(event: PlayerQuitEvent) {
        val sendData = SendData(
            "message",
            "quit",
            Message(
                "send",
                Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                ),
                Utils.componentToString(event.quitMessage() ?: return),
                null
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }


    @EventHandler fun onPlayerDeath(event: PlayerDeathEvent) {
        val sendData = SendData(
            "message",
            "death",
            Message(
                "send",
                Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                ),
                Utils.componentToString(event.deathMessage() ?: return),
                null
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }


    @EventHandler fun onPlayerChat(event: AsyncChatEvent) {
        val sendData = SendData(
            "message",
            "chat",
            Message(
                "send",
                Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                ),
                Utils.componentToString(event.originalMessage()),
                null
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }


    @EventHandler fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        val sendData = SendData(
            "message",
            "advancement",
            Message(
                "send",
                Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                ),
                Utils.componentToString(event.message() ?: return),
                null
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }

}
