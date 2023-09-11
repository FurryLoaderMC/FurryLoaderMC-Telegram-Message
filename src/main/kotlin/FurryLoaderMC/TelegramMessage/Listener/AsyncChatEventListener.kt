package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.TextComponent
import FurryLoaderMC.TelegramMessage.*


class AsyncChatEventListener: Listener {

    @EventHandler fun onAsyncChatEvent(event: AsyncChatEvent) {
        val contentList = mutableListOf<Content>()

        // 玩家消息
        (event.originalMessage() as? TextComponent)?.let {
            contentList.add(Content(
                "text",
                null,
                it.content()
            ))
        }

        // 没有就不要发送了
        if (contentList.isEmpty()) {
            return
        }

        Main.sendMessageToBot("chat", Data(
            Sender(
                event.player.name,
                event.player.uniqueId.toString(),
                null,
                null
            ),
            Message(
                null,
                contentList
            )
        ))
    }

}
