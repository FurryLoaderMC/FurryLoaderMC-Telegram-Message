package FurryLoaderMC.TelegramBot

import FurryLoaderMC.TelegramBot.Main.Companion.instance
import FurryLoaderMC.TelegramBot.Main.Companion.webSocket
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.entity.PlayerDeathEvent
import io.papermc.paper.event.player.AsyncChatEvent


class EventListener: Listener {

    @EventHandler fun onPlayerJoin(event: PlayerJoinEvent) {
        val message = event.joinMessage() ?: return
        instance.server.sendMessage(Utils.furryLoaderMessage(message))
        webSocket.send(Utils.componentToString(message))
    }


    @EventHandler fun onPlayerQuit(event: PlayerQuitEvent) {
        val message = event.quitMessage() ?: return
        instance.server.sendMessage(Utils.furryLoaderMessage(message))
        webSocket.send(Utils.componentToString(message))
    }


    @EventHandler fun onPlayerDeath(event: PlayerDeathEvent) {
        val message = event.deathMessage() ?: return
        instance.server.sendMessage(Utils.furryLoaderMessage(message))
        webSocket.send(Utils.componentToString(message))
    }


    @EventHandler fun onPlayerChat(event: AsyncChatEvent) {
        val message = event.originalMessage()
        instance.server.sendMessage(Utils.furryLoaderMessage(message))
        webSocket.send(Utils.componentToString(message))
    }


    @EventHandler fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        val message = event.message() ?: return
        instance.server.sendMessage(Utils.furryLoaderMessage(message))
        webSocket.send(Utils.componentToString(message))
    }

}
