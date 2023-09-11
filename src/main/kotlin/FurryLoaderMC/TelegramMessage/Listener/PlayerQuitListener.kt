package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import FurryLoaderMC.TelegramMessage.*


private val serializer = PlainTextComponentSerializer.plainText()


class PlayerQuitListener: Listener {

    @EventHandler fun onPlayerQuitEvent(event: PlayerQuitEvent) {
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

}
