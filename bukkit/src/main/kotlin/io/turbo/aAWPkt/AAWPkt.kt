package io.turbo.aAWPkt

import io.turbo.aAWPkt.util.FastInvManager
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

object AAWPkt :JavaPlugin() {
    val mini = MiniMessage.miniMessage()

    override fun onEnable() {
       saveDefaultConfig()
       listOf("",
              "Started AAWP version",
              "").forEach(Bukkit.getConsoleSender()::sendMessage)

        FastInvManager.register(this)
    }
}
