package io.github.yeongin.blocks.Gamble;

import io.github.yeongin.blocks.Gamble.GUI.GambleGUI;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GambleBlock implements Listener {
    ArrayList<Location> locs = new ArrayList<>();
    ItemStack gambleBlock = new GambleBLock_Item().getItem();
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getItemInHand().isSimilar(gambleBlock)){

            Location loc = e.getBlock().getLocation();
            locs.add(loc);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            if(e.getClickedBlock().getType()==gambleBlock.getType())
                if(locs.contains(e.getClickedBlock().getLocation())){
                    e.setCancelled(true);
                    ////여기서 열기
                    GambleGUI gui = new GambleGUI();
                    e.getPlayer().openInventory(gui.getInventory());
                    //gui = null;
                }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Location loc = e.getBlock().getLocation();
        if(locs.contains(loc)){
            locs.remove(loc);
        }
    }

    public void saveLoc(Plugin plugin) throws IOException {
        File f = new File(plugin.getDataFolder().getAbsolutePath(), "gambleBlockLoc.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("gambleBlock.loc", locs);
        c.save(f);
    }

    @SuppressWarnings("unchecked")
    public void loadLoc(Plugin plugin) throws IOException {
        File f = new File(plugin.getDataFolder().getAbsolutePath(), "gambleBlockLoc.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        locs = ((ArrayList<Location>) c.get("gambleBlock.loc"));
    }
}
