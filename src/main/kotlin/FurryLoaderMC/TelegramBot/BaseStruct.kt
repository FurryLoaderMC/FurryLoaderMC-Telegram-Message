package FurryLoaderMC.TelegramBot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SendData<Type>(
    @SerialName("channel") val channel: String,
    @SerialName("event") val event: String,
    @SerialName("content") val content: Type
)


@Serializable
data class Player(
    @SerialName("name") val name: String,
    @SerialName("uuid") val uuid: String
)


@Serializable
data class Message(
    @SerialName("action") val action: String,
    @SerialName("player") val player: Player,
    @SerialName("message") val message: String,
    @SerialName("id") val id: Long?
)


@Serializable
data class OnlinePlayers(
    @SerialName("current") val current: Long,
    @SerialName("maximum") val maximum: Long,
    @SerialName("players") val players: MutableList<Player>
)
