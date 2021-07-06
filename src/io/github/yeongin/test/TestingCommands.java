package io.github.yeongin.test;

import io.github.yeongin.blocks.Gamble.GUI.GambleGUI;
import io.github.yeongin.blocks.Gamble.GambleBLock_Item;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestingCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Consol
        if (!(sender instanceof Player)){
            sender.sendMessage("플레이어만 쓸수있당께?");
            return true;
        }
        //Player
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("gamble")){
            GambleGUI gui = new GambleGUI();
            player.openInventory(gui.getInventory());
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("gamble_item")){
            ItemStack item = new GambleBLock_Item().getItem();
            player.getInventory().addItem(item);
        }
        return false;
    }
}
