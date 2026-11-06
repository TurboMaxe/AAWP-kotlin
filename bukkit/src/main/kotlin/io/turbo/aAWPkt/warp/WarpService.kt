package io.turbo.aAWPkt.warp

import io.turbo.aAWPkt.config.Config
import io.turbo.aAWPkt.warp.types.Warp
import org.bukkit.entity.Player

object WarpService {
    private val cache: MutableList<Warp> = mutableListOf()

    fun create(owner: Player): Warp {
        val warp = Warp(
             owner,
             owner.location,
             owner.world
        )
        cache.add(warp)
        return warp
    }

    fun all(): List<Warp> {
        return cache.toList()
    }

    fun delete(warp: Warp) {
        cache.remove(warp)
    }

    fun reload() {
        Config.reload()
    }
}