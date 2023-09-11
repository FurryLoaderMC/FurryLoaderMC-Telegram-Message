package FurryLoaderMC.TelegramMessage.Listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.TextComponent
import FurryLoaderMC.TelegramMessage.*


class PlayerDeathListener: Listener {

    @EventHandler fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        val contentList = mutableListOf<Content>()

        // 本地化键名
        (event.deathMessage() as? TranslatableComponent)?.let {
            contentList.add(Content(
                "text",
                null,
                it.key()
            ))
        }

        // 死者
        (event.deathMessage() as? TranslatableComponent)?.let {
            // 可能是TextComponent类型
            (it.args().getOrNull(0) as? TextComponent)?.let {
                contentList.add(Content(
                    "text",
                    null,
                    it.content()
                ))
            }
            // 可能是TranslatableComponent类型
            (it.args().getOrNull(0) as? TranslatableComponent)?.let {
                contentList.add(Content(
                    "text",
                    null,
                    it.key()
                ))
            }
        }

        // 击杀者（也可能是物品，因为death.attack.onFire.item，但是问题不大）
        (event.deathMessage() as? TranslatableComponent)?.let {
            // 可能是TextComponent类型
            (it.args().getOrNull(1) as? TextComponent)?.let {
                contentList.add(Content(
                    "text",
                    null,
                    it.content()
                ))
            }
            // 可能是TranslatableComponent类型
            (it.args().getOrNull(1) as? TranslatableComponent)?.let {
                contentList.add(Content(
                    "text",
                    null,
                    it.key()
                ))
            }
        }

        // 物品（也可能是击杀者，因为death.attack.onFire.item，但是问题不大）
        (event.deathMessage() as? TranslatableComponent)?.let {
            (it.args().getOrNull(2) as? TranslatableComponent)?.let {
                (it.args().getOrNull(0) as? TextComponent)?.let {
                    (it.children().getOrNull(0) as? TextComponent)?.let {
                        contentList.add(Content(
                            "text",
                            null,
                            it.content()
                        ))
                    }
                }
            }
        }

        // 没有就不要发送了
        if (contentList.isEmpty()) {
            return
        }

        Main.sendMessageToBot("death", Data(
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
