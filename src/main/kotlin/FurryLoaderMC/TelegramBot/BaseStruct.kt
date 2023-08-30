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
     @SerialName("player") val player: Player,
     @SerialName("message") val message: String,
)


@Serializable
data class OnlinePlayers(
     @SerialName("current") val current: Int,
     @SerialName("maximum") val maximum: Int,
     @SerialName("players") val players: MutableList<Player>
)
