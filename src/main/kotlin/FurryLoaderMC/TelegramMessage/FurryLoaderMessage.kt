package FurryLoaderMC.TelegramMessage

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor


class FurryLoaderMessage {

    fun sendMessage(data: Data) {
        val typeComponents = mutableListOf<Component>()
        data.message.content.forEach {
            val typeComponent = this.getTypeComponent(it)
            typeComponents.add(typeComponent)
        }
        typeComponents.add(Component.text(" ")) // 防止单个type下hover只显示整体消息的
        val componentMessage = this.buildMessage(data, typeComponents)
        Main.instance.server.sendMessage(componentMessage)
    }


    private fun buildMessage(data: Data, typeComponents: MutableList<Component>): Component {
        val nameClick = ClickEvent.suggestCommand("/telegram at ${data.sender.telegramID} ")
        val nameHover = HoverEvent.showText(
            Component.join(
                JoinConfiguration.separator(Component.newline()),
                Component.text("点击即可在 Telegram 提及"),
                Component.join(
                    JoinConfiguration.separator(Component.text(" ")),
                    Component.text("Minecraft Name:").color(NamedTextColor.DARK_PURPLE),
                    Component.text(data.sender.minecraftName),
                ),
                Component.join(
                    JoinConfiguration.separator(Component.text(" ")),
                    Component.text("Telegram ID:").color(NamedTextColor.DARK_PURPLE),
                    Component.text(data.sender.telegramID.toString()),
                )
            )
        )

        val contentClick = ClickEvent.suggestCommand("/telegram reply ${data.message.id} ")
        val contentHover = HoverEvent.showText(
            Component.join(
                JoinConfiguration.separator(Component.newline()),
                Component.text("点击即可在 Telegram 回复"),
                Component.join(
                    JoinConfiguration.separator(Component.text(" ")),
                    Component.text("Message ID:").color(NamedTextColor.DARK_PURPLE),
                    Component.text(data.message.id.toString()),
                )
            )
        )

        return Component.join(
            JoinConfiguration.separator(Component.text(" ")),
            Main.pluginConfig.getString("message.prefix")?.let {
                Component.text(it).color(NamedTextColor.GREEN)
            } ?: Component.empty() ,
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("<"),
                Component.text(data.sender.telegramName!!).clickEvent(nameClick).hoverEvent(nameHover),
                Component.text(">")
            ).color(NamedTextColor.AQUA),
            Component.join(
                JoinConfiguration.separator(Component.text(" ")),
                typeComponents
            ).hoverEvent(contentHover).clickEvent(contentClick)
        )
    }


    private fun getTypeComponent(content: Content): Component {
        if (content.type == "text") {
            return Component.text(content.content!!)
        }
        return when (content.type) {
            "at" -> Component.text("@${content.content}")
            "reply" -> Component.text("[回复]")
            "sticker" -> Component.text("[贴纸]")
            "photo" -> Component.text("[图片]")
            "video" -> Component.text("[视频]")
            "audio" -> Component.text("[音频]")
            "voice" -> Component.text("[语音]")
            "document" -> Component.text("[文件]")
            else -> Component.text(content.content ?: "")
        }.color(NamedTextColor.GOLD).hoverEvent(this.buildTypeHoverEvent(content))
    }


    private fun buildTypeHoverEvent(content: Content): HoverEvent<Component> {
        val contentContent = content.content?.let {
            Component.join(
                JoinConfiguration.separator(Component.text(" ")),
                Component.text("Content:").color(NamedTextColor.DARK_PURPLE),
                Component.text(it)
            )
        } ?: Component.empty()

        val contentId = content.id?.let {
            Component.join(
                JoinConfiguration.separator(Component.text(" ")),
                Component.text("ID:").color(NamedTextColor.DARK_PURPLE),
                Component.text(it)
            )
        } ?: Component.empty()

        return HoverEvent.showText(
            Component.join(
                JoinConfiguration.separator(Component.newline()),
                contentContent,
                contentId
            )
        )
    }

}
