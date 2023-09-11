package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import net.kyori.adventure.text.TranslatableComponent
import FurryLoaderMC.TelegramMessage.*


class PlayerAdvancementDoneListener: Listener {

    @EventHandler fun onPlayerAdvancementDoneEvent(event: PlayerAdvancementDoneEvent) {
        val contentList = mutableListOf<Content>()

        // 类型
        (event.message() as? TranslatableComponent)?.let {
            contentList.add(Content(
                "text",
                null,
                it.key()
            ))
        }

        // 标题
        (event.message() as? TranslatableComponent)?.let {
            (it.args().getOrNull(1) as? TranslatableComponent)?.let {
                (it.args().getOrNull(0) as? TranslatableComponent)?.let {
                    contentList.add(Content(
                        "text",
                        null,
                        it.key()
                    ))
                }
            }
        }

        // 描述
        (event.message() as? TranslatableComponent)?.let {
            (it.args().getOrNull(1) as? TranslatableComponent)?.let {
                (it.args().getOrNull(0) as? TranslatableComponent)?.let {
                    (it.style().hoverEvent()?.value() as? TranslatableComponent)?.let {
                        (it.children().getOrNull(1) as? TranslatableComponent)?.let {
                            contentList.add(Content(
                                "text",
                                null,
                                it.key()
                            ))
                        }
                    }
                }
            }
        }

        // 没有就不要发送了
        if (contentList.isEmpty()) {
            return
        }

        Main.sendMessageToBot("advancement", Data(
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
