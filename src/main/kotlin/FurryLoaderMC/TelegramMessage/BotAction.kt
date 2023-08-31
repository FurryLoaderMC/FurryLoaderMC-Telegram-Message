package FurryLoaderMC.TelegramMessage

import kotlinx.serialization.json.*


class BotAction {

    fun onMessage(message: String) {
        val data = Json.decodeFromString<Data>(message)
        when (data.channel) {
            "message" -> when (data.event) {
                "chat" -> this.sendMessage(Json.decodeFromJsonElement<Chat>(data.content))
            }
            "status" -> when (data.event) {
                "onlinePlayers" -> this.getOnlinePlayers()
            }
        }
    }


    private fun sendMessage(chat: Chat) {
        val sender = Json.decodeFromJsonElement<Sender>(chat.sender)
        Main.sendMessageToGame(sender, chat.message)
    }


    private fun getOnlinePlayers() {
        val players = mutableListOf<Player>()
        Main.instance.server.onlinePlayers.forEach {
            val content = Player(
                it.name,
                it.uniqueId.toString()
            )
            players.add(content)
        }
        Main.sendMessageToBot(Data(
            "status",
            "onlinePlayers",
            Json.encodeToJsonElement(OnlinePlayers(
                players.size.toLong(),
                Main.instance.server.maxPlayers.toLong(),
                players
            ))
        ))
    }

}
