package io.github.yeongin.blocks.Gamble.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.PlayerInventory;

public class GUIEventHandler implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getInventory().getHolder() instanceof GUIH) {

            e.setCancelled(true);
            GUIH gui = (GUIH) e.getInventory().getHolder();
            gui.onGUIClick((Player) e.getWhoClicked(), e.getRawSlot(), e.getCurrentItem(), e);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof GUIH) {
            GUIH gui = (GUIH) e.getInventory().getHolder();
            gui.onGUIClose((Player) e.getPlayer());

        }
    }
}
//!(e.getClickedInventory() instanceof PlayerInventory)
