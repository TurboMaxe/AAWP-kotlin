package io.turbo.aAWPkt.warp.types

import de.exlll.configlib.Configuration
import io.turbo.aAWPkt.warp.types.BaseWarp
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.gradle.internal.impldep.org.bouncycastle.asn1.x500.style.RFC4519Style.owner

@Configuration
data class Warp @JvmOverloads constructor(
    val owner: Player,
    val location: Location,
    val world: World,
    val name: String = owner.name
) : BaseWarp {

    override fun isOverworld(): Boolean {
        return world.environment == World.Environment.NORMAL
    }


}