package FurryLoaderMC.TelegramMessage

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.text.format.NamedTextColor


object Utils {

    fun furryLoaderMessage(
        telegram: String,
        minecraft: String,
        message: String,
        telegram_id: Long,
        message_id: Long
    ): Component {
        val nameHover = HoverEvent.showText(Component.join(
            JoinConfiguration.separator(Component.newline()),
            Component.text("点击即可在 Telegram 提及"),
            Component.join(
                JoinConfiguration.separator(Component.text(" ")),
                Component.text("Minecraft ID:").color(NamedTextColor.DARK_PURPLE),
                Component.text(minecraft),
            ),
            Component.join(
                JoinConfiguration.separator(Component.text(" ")),
                Component.text("Telegram ID:").color(NamedTextColor.DARK_PURPLE),
                Component.text(telegram_id.toString()),
            )
        ))

        val nameClick = ClickEvent.suggestCommand("/telegram at $telegram_id ")

        val contentHover = HoverEvent.showText(Component.join(
            JoinConfiguration.separator(Component.newline()),
            Component.text("点击即可在 Telegram 回复"),
            Component.join(
                JoinConfiguration.separator(Component.text(" ")),
                Component.text("Message ID:").color(NamedTextColor.DARK_PURPLE),
                Component.text(message_id.toString()),
            )
        ))

        val contentClick = ClickEvent.suggestCommand("/telegram reply $message_id ")

        return Component.join(
            JoinConfiguration.separator(Component.text(" ")),
            Component.text("[FurryLoader]").color(NamedTextColor.GREEN),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("<"),
                Component.text(telegram).hoverEvent(nameHover).clickEvent(nameClick),
                Component.text(">")
            ).color(NamedTextColor.AQUA),
            Component.text(message).hoverEvent(contentHover).clickEvent(contentClick)
        )
    }


    fun componentToString(content: Component): String {
        val serializer = PlainTextComponentSerializer.plainText()
        return serializer.serialize(content)
    }

}
