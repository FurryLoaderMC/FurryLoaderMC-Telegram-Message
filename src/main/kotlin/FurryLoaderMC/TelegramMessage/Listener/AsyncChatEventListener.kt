package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import FurryLoaderMC.TelegramMessage.*


private val serializer = PlainTextComponentSerializer.plainText()


class AsyncChatEventListener: Listener {

    @EventHandler fun onAsyncChatEvent(event: AsyncChatEvent) {
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

}
