package FurryLoaderMC.TelegramBot

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.text.format.NamedTextColor


object Utils {

    fun furryLoaderMessage(content: Component): Component {
        val header = Component.text("[FurryLoader] ").color(NamedTextColor.GREEN)
        return header.append(content.color(NamedTextColor.WHITE))
    }


    fun furryLoaderMessage(content: String): Component {
        val header = Component.text("[FurryLoader] ").color(NamedTextColor.GREEN)
        return header.append(Component.text(content).color(NamedTextColor.WHITE))
    }


    fun componentToString(content: Component): String {
        val serializer = PlainTextComponentSerializer.plainText()
        return serializer.serialize(content)
    }

}