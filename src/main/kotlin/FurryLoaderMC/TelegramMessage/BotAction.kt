package FurryLoaderMC.TelegramMessage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*


class BotAction {

    fun onMessage(message: String) {
        val json = Json.parseToJsonElement(message)
        val channel = json.jsonObject["channel"]?.jsonPrimitive?.content ?: return
        val event = json.jsonObject["event"]?.jsonPrimitive?.content ?: return
        val content = json.jsonObject["content"] ?: return
        when (channel) {
            "message" -> when (event) {
                "chat" -> this.sendMessage(content)
            }
            "status" -> when (event) {
                "onlinePlayers" -> this.getOnlinePlayers(channel, event)
            }
        }
    }


    private fun sendMessage(json: JsonElement) {
        val telegram = json.jsonObject["telegram"]?.jsonPrimitive?.content ?: return
        val minecraft = json.jsonObject["minecraft"]?.jsonPrimitive?.content ?: return
        val message = json.jsonObject["message"]?.jsonPrimitive?.content ?: return
        val telegramId = json.jsonObject["telegram_id"]?.jsonPrimitive?.long ?: return
        val messageId = json.jsonObject["message_id"]?.jsonPrimitive?.long ?: return
        Main.sendMessageToGame(telegram, minecraft, message, telegramId, messageId)
    }


    private fun getOnlinePlayers(channel: String, event: String) {
        val players = mutableListOf<Player>()
        Main.instance.server.onlinePlayers.forEach {
            val content = Player(
                it.name,
                it.uniqueId.toString()
            )
            players.add(content)
        }
        val sendData = SendData(
            channel,
            event,
            OnlinePlayers(
                players.size.toLong(),
                Main.instance.server.maxPlayers.toLong(),
                players
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }

}