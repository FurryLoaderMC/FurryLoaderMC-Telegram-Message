package FurryLoaderMC.TelegramMessage

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.entity.PlayerDeathEvent
import io.papermc.paper.event.player.AsyncChatEvent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer


private val serializer = PlainTextComponentSerializer.plainText()


class EventListener: Listener {

    @EventHandler fun onPlayerJoin(event: PlayerJoinEvent) {
        Main.sendMessageToBot(Data(
            "message",
            "join",
            Json.encodeToJsonElement(Chat(
                Json.encodeToJsonElement(Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                )),
                Message(
                    null,
                    listOf(Content(
                        "text",
                        null,
                        serializer.serialize(event.joinMessage() ?: return)
                    ))
                )
            ))
        ))
    }


    @EventHandler fun onPlayerQuit(event: PlayerQuitEvent) {
        Main.sendMessageToBot(Data(
            "message",
            "quit",
            Json.encodeToJsonElement(Chat(
                Json.encodeToJsonElement(Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                )),
                Message(
                    null,
                    listOf(Content(
                        "text",
                        null,
                        serializer.serialize(event.quitMessage() ?: return)
                    ))
                )
            ))
        ))
    }


    @EventHandler fun onPlayerDeath(event: PlayerDeathEvent) {
        Main.sendMessageToBot(Data(
            "message",
            "death",
            Json.encodeToJsonElement(Chat(
                Json.encodeToJsonElement(Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                )),
                Message(
                    null,
                    listOf(Content(
                        "text",
                        null,
                        serializer.serialize(event.deathMessage() ?: return)
                    ))
                )
            ))
        ))
    }


    @EventHandler fun onPlayerChat(event: AsyncChatEvent) {
        Main.sendMessageToBot(Data(
            "message",
            "chat",
            Json.encodeToJsonElement(Chat(
                Json.encodeToJsonElement(Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                )),
                Message(
                    null,
                    listOf(Content(
                        "text",
                        null,
                        serializer.serialize(event.originalMessage())
                    ))
                )
            ))
        ))
    }


    @EventHandler fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        Main.sendMessageToBot(Data(
            "message",
            "advancement",
            Json.encodeToJsonElement(Chat(
                Json.encodeToJsonElement(Player(
                    event.player.name,
                    event.player.uniqueId.toString()
                )),
                Message(
                    null,
                    listOf(Content(
                        "text",
                        null,
                        serializer.serialize(event.message() ?: return)
                    ))
                )
            ))
        ))
    }

}
