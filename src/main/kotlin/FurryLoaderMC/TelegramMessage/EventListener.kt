package FurryLoaderMC.TelegramMessage

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.entity.PlayerDeathEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer


private val serializer = PlainTextComponentSerializer.plainText()


class EventListener: Listener {

    @EventHandler fun onPlayerJoin(event: PlayerJoinEvent) {
        Main.sendMessageToBot("join" ,Data(
            Sender(
                event.player.name,
                event.player.uniqueId.toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(Content(
                    "text",
                    null,
                    serializer.serialize(event.joinMessage() ?: return)
                ))
            )
        ))
    }


    @EventHandler fun onPlayerQuit(event: PlayerQuitEvent) {
        Main.sendMessageToBot("quit", Data(
            Sender(
                event.player.name,
                event.player.uniqueId.toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(Content(
                    "text",
                    null,
                    serializer.serialize(event.quitMessage() ?: return)
                ))
            )
        ))
    }


    @EventHandler fun onPlayerDeath(event: PlayerDeathEvent) {
        Main.sendMessageToBot("death", Data(
            Sender(
                event.player.name,
                event.player.uniqueId.toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(Content(
                    "text",
                    null,
                    serializer.serialize(event.deathMessage() ?: return)
                ))
            )
        ))
    }


    @EventHandler fun onPlayerChat(event: AsyncChatEvent) {
        Main.sendMessageToBot("chat", Data(
            Sender(
                event.player.name,
                event.player.uniqueId.toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(Content(
                    "text",
                    null,
                    serializer.serialize(event.originalMessage())
                ))
            )
        ))
    }


    @EventHandler fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        Main.sendMessageToBot("advancement", Data(
            Sender(
                event.player.name,
                event.player.uniqueId.toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(Content(
                    "text",
                    null,
                    serializer.serialize(event.message() ?: return)
                ))
            )
        ))
    }

}
