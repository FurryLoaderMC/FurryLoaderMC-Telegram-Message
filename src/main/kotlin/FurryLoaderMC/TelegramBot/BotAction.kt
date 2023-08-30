package FurryLoaderMC.TelegramBot

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


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
        Main.sendMessageToGame(telegram, minecraft, message)
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
                players.size,
                Main.instance.server.maxPlayers,
                players
            )
        )
        Main.sendMessageToBot(Json.encodeToString(sendData))
    }

}