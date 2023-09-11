package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import FurryLoaderMC.TelegramMessage.*


private val serializer = PlainTextComponentSerializer.plainText()


class PlayerDeathListener: Listener {

    @EventHandler fun onPlayerDeathEvent(event: PlayerDeathEvent) {
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

}
