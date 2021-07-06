package io.github.yeongin.blocks.Gamble.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GUIH implements InventoryHolder {
    public void onGUIClick(Player whoClicked, int slot, ItemStack clickedItem, InventoryClickEvent e){};

    public void onGUIClose(Player whoClosed){}

    @Override
    public Inventory getInventory() {
        return null;
    }
}
