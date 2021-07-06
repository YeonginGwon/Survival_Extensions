package io.github.yeongin;

import io.github.yeongin.blocks.Gamble.GUI.GUIEventHandler;
import io.github.yeongin.blocks.Gamble.GambleBlock;
import io.github.yeongin.test.TestingCommands;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {
    private GambleBlock gb;
    @Override
    public void onEnable() {
        super.onEnable();
        PluginManager pm =getServer().getPluginManager();
        gb = new GambleBlock();

        // @EventHandler
        pm.registerEvents(new GUIEventHandler(),this);
        pm.registerEvents(gb,this);

        //Suvival Extentions Define
        getCommand("gamble").setExecutor(new TestingCommands());
        getCommand("gamble_item").setExecutor(new TestingCommands());
        getLogger().info( ChatColor.LIGHT_PURPLE+"==== 로딩 완료 ====");

        //Loads
        try {
            loadGB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

        super.onDisable();
        saveGB();
    }

    private void saveGB(){
        try {
            gb.saveLoc(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGB() throws IOException {
        gb.loadLoc(this);
    }
}
