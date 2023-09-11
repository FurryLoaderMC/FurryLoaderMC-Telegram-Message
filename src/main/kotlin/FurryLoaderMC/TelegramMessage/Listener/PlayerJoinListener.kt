package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.TextComponent
import FurryLoaderMC.TelegramMessage.*


class PlayerJoinListener: Listener {

    @EventHandler fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val contentList = mutableListOf<Content>()

        // 本地化键名
        (event.joinMessage() as? TranslatableComponent)?.let {
            contentList.add(Content(
                "text",
                null,
                it.key()
            ))
        }

        // 玩家
        (event.joinMessage() as? TranslatableComponent)?.let {
            (it.args().getOrNull(0) as? TextComponent)?.let {
                contentList.add(Content(
                    "text",
                    null,
                    it.content()
                ))
            }
        }

        // 没有就不要发送了
        if (contentList.isEmpty()) {
            return
        }

        Main.sendMessageToBot("join", Data(
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
