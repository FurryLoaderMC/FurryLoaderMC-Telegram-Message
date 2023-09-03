package FurryLoaderMC.TelegramMessage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Sender(
    @SerialName("minecraft_name") val minecraftName: String,
    @SerialName("minecraft_uuid") val minecraftUUID: String,
    @SerialName("telegram_name") val telegramName: String?,
    @SerialName("telegram_id") val telegramID: Long?
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
data class Data(
    @SerialName("sender") val sender: Sender,
    @SerialName("message") val message: Message,
)
