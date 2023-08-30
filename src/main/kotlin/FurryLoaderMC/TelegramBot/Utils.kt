package FurryLoaderMC.TelegramBot

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.text.format.NamedTextColor


object Utils {

    fun furryLoaderMessage(telegram: String, minecraft: String, message: String): Component {
        return Component.join(
            JoinConfiguration.separator(Component.text(" ")),
            Component.text("[FurryLoader]").color(NamedTextColor.GREEN),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("<"),
                Component.text(telegram).hoverEvent(HoverEvent.showText(Component.text(minecraft))),
                Component.text(">")
            ).color(NamedTextColor.YELLOW),
            Component.text(message)
        )
    }


    fun componentToString(content: Component): String {
        val serializer = PlainTextComponentSerializer.plainText()
        return serializer.serialize(content)
    }

}
