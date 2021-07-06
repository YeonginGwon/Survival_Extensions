package io.github.yeongin.blocks.Gamble;

import io.github.yeongin.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class GambleBLock_Item {
    private ItemStack gambleBlock;
    public GambleBLock_Item(){
        ItemBuilder ib = new ItemBuilder(Material.GOLD_BLOCK).setName("아홀로틀의 은혜").addLore("이 작은 친구는 아이템을 물어옵니다!").addLore("항상 성공하진 못하지만, 항상 귀엽죠.").addEnchant(Enchantment.LUCK, 4);
        gambleBlock = ib.toItemStack();
    }

    public ItemStack getItem(){
        return gambleBlock;
    }
}
