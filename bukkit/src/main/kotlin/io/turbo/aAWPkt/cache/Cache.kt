package io.turbo.aAWPkt.cache

import io.turbo.aAWPkt.config.Config
import io.turbo.aAWPkt.warp.types.Warp
import kotlin.collections.toList

object Cache {
    private val warps: MutableList<Warp> = mutableListOf<Warp>()

    fun cache(): Cache {
      Config.instance().warps.forEach(warps::add)
      return this
    }

    fun put(warp: Warp) {
        warps.add(warp)
    }

    fun fetch(warp: Warp): Warp {
        return warps.get(warps.indexOf(warp))
    }

    fun invalidate(warp: Warp) {
      warps.remove(warp)
      Config.instance().warps.remove(warp)
    }

    fun all(): List<Warp> = warps.toList()

    fun config(): List<Warp> = Config.instance().warps
}