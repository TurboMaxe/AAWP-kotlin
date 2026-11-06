package io.turbo.aAWPkt.menu

import io.turbo.aAWPkt.AAWPkt
import io.turbo.aAWPkt.cache.Cache
import io.turbo.aAWPkt.util.FastInv
import io.turbo.aAWPkt.util.item.ItemBuilder
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent


class WarpMenu(

) : FastInv(27, "Confirmation GUI") {

     val preventClose = false

     init {
        setItem(
            12,
            ItemBuilder(Material.LIME_SHULKER_BOX).name("<green><bold>Click me to delete all warps!</bold></green>")
                .build()
        ) { e -> e.whoClicked.sendMessage(AAWPkt.mini.deserialize("<yellow><bold>WARPS:-</bold></yellow> <yellow>Attempting to delete all warps...")) }
         setItem(
            14,
            ItemBuilder(Material.RED_SHULKER_BOX).name("<red><bold>Click me to exit this gui!</bold></red>")
                .build()
         ) { e -> e.whoClicked.sendMessage(AAWPkt.mini.deserialize("<yellow><bold>WARPS:-</bold></yellow> <yellow>Exiting gui...")) }
         setItems(getBorders(), ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(".").build())
        setCloseFilter { p -> this.preventClose  }
     }

    public override fun onClick(event: InventoryClickEvent) {
        if (event.slot == 12) {
            if (Cache.cache().all().isEmpty()) {
                event.whoClicked.sendMessage(AAWPkt.mini.deserialize("There are no warps to delete!").color(TextColor.color(0xFF0009)))
                event.isCancelled = true
            }

            event.whoClicked.sendMessage(AAWPkt.mini.deserialize(
                    "<green>Successfully deleted all warps!</green>")
            )
            event.inventory.close()
        }

        if (event.slot == 14) {
            event.inventory.close()
            event.whoClicked.sendMessage(AAWPkt.mini.deserialize("Cancelling action.").color(TextColor.color(0xFFF936)))
        }
    }


}