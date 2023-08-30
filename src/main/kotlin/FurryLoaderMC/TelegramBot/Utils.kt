package FurryLoaderMC.TelegramBot

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.text.format.NamedTextColor


object Utils {

    fun furryLoaderMessage(telegram: String, minecraft: String, message: String): Component {
        val header = Component.text("[FurryLoader] ").color(NamedTextColor.GREEN)
        val name = Component.text("<${telegram}>(${minecraft}) ").color(NamedTextColor.YELLOW)
        val content = Component.text(message).color(NamedTextColor.WHITE)
        return Component.text("").append(header).append(name).append(content)
    }


    fun componentToString(content: Component): String {
        val serializer = PlainTextComponentSerializer.plainText()
        return serializer.serialize(content)
    }

}