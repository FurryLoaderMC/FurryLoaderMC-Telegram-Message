package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import FurryLoaderMC.TelegramMessage.*


private val serializer = PlainTextComponentSerializer.plainText()


class PlayerAdvancementDoneListener: Listener {

    @EventHandler fun onPlayerAdvancementDoneEvent(event: PlayerAdvancementDoneEvent) {
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
