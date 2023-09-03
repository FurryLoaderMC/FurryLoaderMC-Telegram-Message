package FurryLoaderMC.TelegramMessage.Command

import FurryLoaderMC.TelegramMessage.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class Executor : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            return this.help(sender)
        }

        when (args[0]) {
            "help" -> return this.help(sender)
            "send" -> return this.send(sender, args)
            "at" -> return this.at(sender, args)
            "reply" -> return this.reply(sender, args)
        }

        return false
    }


    private fun help(sender: CommandSender):Boolean {
        sender.sendMessage(Component.join(
            JoinConfiguration.separator(Component.newline()),
            Component.text("--------------- FurryLoaderMC - Telegram Bot ---------------"),
            Component.text("快捷用法：").color(NamedTextColor.AQUA),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("发送：").color(NamedTextColor.GOLD),
                Component.text("在聊天框内直接发送消息即可，也可以使用指令发送"),
            ),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("提及：").color(NamedTextColor.GOLD),
                Component.text("将指针移动至Bot消息对应名称上，点击即可快速提及"),
            ),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("回复：").color(NamedTextColor.GOLD),
                Component.text("将指针移动至Bot消息对应内容上，点击即可快速回复"),
            ),
            Component.text("指令用法：").color(NamedTextColor.AQUA),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("帮助：").color(NamedTextColor.GOLD),
                Component.text("/telegram help"),
            ),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("发送：").color(NamedTextColor.GOLD),
                Component.text("/telegram send <内容>"),
            ),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("提及：").color(NamedTextColor.GOLD),
                Component.text("/telegram at <账号ID> <内容>"),
            ),
            Component.join(
                JoinConfiguration.separator(null),
                Component.text("回复：").color(NamedTextColor.GOLD),
                Component.text("/telegram reply <消息ID> <内容>"),
            ),
            Component.text("--------------- FurryLoaderMC - Telegram Bot ---------------")
        ))

        return true
    }


    private fun send(sender: CommandSender, args: Array<out String>):Boolean {
        if (args.size < 2) {
            sender.sendMessage("Usage: /telegram send <内容>")
            return true
        }

        Main.sendMessageToBot("chat", Data(
            Sender(
                sender.name,
                Main.instance.server.getPlayerUniqueId(sender.name).toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(
                    Content(
                        "text",
                        null,
                        args[1],
                    )
                )
            )
        ))

        return true
    }


    private fun at(sender: CommandSender, args: Array<out String>):Boolean {
        if (args.size < 3) {
            sender.sendMessage("Usage: /telegram at <账号ID> <内容>")
            return true
        }

        Main.sendMessageToBot("chat", Data(
            Sender(
                sender.name,
                Main.instance.server.getPlayerUniqueId(sender.name).toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(
                    Content(
                        "at",
                        args[1].toLong(),
                        null,
                    ),
                    Content(
                        "text",
                        null,
                        args[2]
                    )
                )
            )
        ))

        return true
    }


    private fun reply(sender: CommandSender, args: Array<out String>):Boolean {
        if (args.size < 3) {
            sender.sendMessage("Usage: /telegram reply <消息ID> <内容>")
            return true
        }

        Main.sendMessageToBot("chat", Data(
            Sender(
                sender.name,
                Main.instance.server.getPlayerUniqueId(sender.name).toString(),
                null,
                null
            ),
            Message(
                null,
                listOf(
                    Content(
                        "reply",
                        args[1].toLong(),
                        null,
                    ),
                    Content(
                        "text",
                        null,
                        args[2]
                    )
                )
            )
        ))

        return true
    }

}
