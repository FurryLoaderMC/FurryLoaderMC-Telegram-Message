package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import FurryLoaderMC.TelegramMessage.*


private val serializer = PlainTextComponentSerializer.plainText()


class PlayerJoinListener: Listener {

    @EventHandler fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        Main.sendMessageToBot("join", Data(
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
        )
        )
    }

}
