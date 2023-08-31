package FurryLoaderMC.TelegramMessage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement


@Serializable
data class Player(
    @SerialName("name") val name: String,
    @SerialName("uuid") val uuid: String
)


@Serializable
data class Sender(
    @SerialName("minecraft_name") val minecraftName: String,
    @SerialName("telegram_name") val telegramName: String,
    @SerialName("telegram_id") val telegramId: Long
)


@Serializable
data class Content(
    @SerialName("type") val type: String,
    @SerialName("id") val id: Long?,
    @SerialName("content") val content: String?
)


@Serializable
data class Message(
    @SerialName("id") val id: Long?,
    @SerialName("content") val content: List<Content>
)


@Serializable
data class Chat(
    @SerialName("sender") val sender: JsonElement,
    @SerialName("message") val message: Message,
)


@Serializable
data class OnlinePlayers(
    @SerialName("current") val current: Long,
    @SerialName("maximum") val maximum: Long,
    @SerialName("players") val players: List<Player>
)


@Serializable
data class Data(
    @SerialName("channel") val channel: String,
    @SerialName("event") val event: String,
    @SerialName("content") val content: JsonElement
)
