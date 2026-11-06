package io.turbo.aAWPkt.command

import io.turbo.aAWPkt.AAWPkt
import io.turbo.aAWPkt.AAWPkt.mini
import io.turbo.aAWPkt.cache.Cache
import io.turbo.aAWPkt.warp.WarpService
import io.turbo.aAWPkt.warp.types.Warp
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.Permission
import org.incendo.cloud.paper.util.sender.EntitySource
import org.incendo.cloud.paper.util.sender.Source

@Command("warp")
object WarpCommand {


    @Command("reload")
    @Permission("warp.admin")
    fun reload() {
       WarpService.reload()
       mini.deserialize("<yellow><bold>WARPS</bold></yellow> Reloaded plugin")
    }

    @Command("list")
    @Permission("warp.list")
    fun list(
        source: Source
    ) {
       mini.deserialize("<yellow><bold>WARPS:-</bold></yellow>")
       Cache.cache().all().forEach {
           listOf(AAWPkt.mini.deserialize("<yellow><bold>WARP-</bold></yellow> <green>${it.name}}</green>"),
                  AAWPkt.mini.deserialize("located at <purple>${it.location.x} >${it.location.y} >${it.location.z}</purple>")
           ).forEach(source.source()::sendMessage)
       }
    }

    @Command("warp [name]")
    @Permission("warp.warp")
    fun warp(
        source: Source,
        name: String
    ) {
       val destWarp: Warp? by lazy {
           for (warp in Cache.cache().all()) if (warp.name == name) {
               warp.name
           }
           source.source().sendMessage(mini.deserialize("<yellow><bold>WARPS/bold></yellow> <red>Could not find any warps by that name!</red>"))
           return@lazy null
       }

       if (destWarp == null) {
           return
       }

       if (source is EntitySource) {
           source.source().sendMessage(
               mini.deserialize(
               "<yellow><bold>WARPS/bold></yellow> Sending ${source.source().name}} to warp $name"
           ))
           source.source().teleportAsync(destWarp!!.location)
       }
    }


    @Command("warp delete [name]")
    @Permission("warp.delete")
    fun delete(
        source: Source,
        name: String
    ) {
        val warp: Warp? by lazy {
            for (warp in Cache.cache().all()) if (warp.name == name) {
               return@lazy warp
            }
            source.source().sendMessage(mini.deserialize("<yellow><bold>WARPS/bold></yellow> <red>Could not find any warps by that name!</red>"))
            return@lazy null
        }
        if (warp != null) {
            Cache.cache().invalidate(warp!!)
        }
    }
}