package io.github.yeongin.blocks.Gamble.GUI;

import io.github.yeongin.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GambleGUI extends GUIH {
    /**
     * 0 = 처음,
     * 1 = 성공,
     * 2 = 실패
     */
    private int _currentPage = 0;
    private ItemStack battedItem = null;
    private boolean hasItem = false;

    @Override
    public Inventory getInventory() {

        Inventory GUI = Bukkit.createInventory(this, 27, "Current page: " + _currentPage + 1);

        //처음
        if (_currentPage == 0) {
            for (int i = 0; i < 9; i++) {
                GUI.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("").toItemStack());
            }
            for (int i = 9; i < 27; i++) {
                GUI.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("").toItemStack());
            }
            GUI.setItem(4, new ItemBuilder(Material.AIR).toItemStack());
            GUI.setItem(21, new ItemBuilder(Material.BOOK).setName("사용법").toItemStack());
            GUI.setItem(22, new ItemBuilder(Material.BARRIER).setName("취소").toItemStack());

            //if(hasItem == false){
                GUI.setItem(23, new ItemBuilder(Material.RED_DYE).setName("시작할수없습니다!").toItemStack());
            //}else {
            //    GUI.setItem(23, new ItemBuilder(Material.FIREWORK_ROCKET).setName("시작!").toItemStack());
            //}
        }
        //성공
        else if (_currentPage == 1){
            for (int i = 0; i < 27; i++) {
                GUI.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("").toItemStack());
            }
            for (int i = 9; i < 18; i++) {
                GUI.setItem(i, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("").toItemStack());
            }
            GUI.setItem(11, battedItem);
            GUI.setItem(13, new ItemBuilder(Material.AXOLOTL_BUCKET).setName("성공!").addLore("아홀로틀이 축하해주네요!").toItemStack());
            GUI.setItem(15, battedItem);
        }
        //실패
        else if (_currentPage == 2){
            for (int i = 0; i < 27; i++) {
                GUI.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("").toItemStack());
            }
            for (int i = 9; i < 18; i++) {
                GUI.setItem(i, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("").toItemStack());
            }
            GUI.setItem(13, new ItemBuilder(Material.BARRIER).setName(" 실패..").addLore("운이없었네요").toItemStack());
        }



        return GUI;
    }

    @Override
    public void onGUIClick(Player whoClicked, int slot, ItemStack clickedItem, InventoryClickEvent e) {
        if(_currentPage == 0){
            if(e.getClickedInventory() instanceof PlayerInventory) {
                e.setCancelled(false);
                if (whoClicked.getOpenInventory().getItem(4)==null || whoClicked.getOpenInventory().getItem(4).getType().equals(Material.AIR)){
                    if(e.getClick().equals(ClickType.SHIFT_LEFT)){
                        hasItem = true;
                    }
                }
            }
            /// 여기밑으로는 커스텀 GUI가 클릭됐을때만

            if(slot == 4){
                e.setCancelled(false);
                if(!e.getCursor().getType().equals(Material.AIR)){
                    hasItem = true;
                } else if(!whoClicked.getOpenInventory().getItem(4).getType().equals(Material.AIR) && e.getCursor().getType().equals(Material.AIR)){
                    hasItem = false;
                }
            }
            if(slot == 22){
                whoClicked.closeInventory();
            }
            /// 뽑기 ///
            if(slot == 23 && hasItem == true){
                setBattedItem(whoClicked.getOpenInventory().getItem(4));
                if (Math.random() < 0.5){
                    whoClicked.openInventory(this.setPage(1).getInventory());
                }
                else {
                    whoClicked.openInventory(this.setPage(2).getInventory());
                }

                return;
            }
            if (hasItem)
                whoClicked.getOpenInventory().setItem(23, new ItemBuilder(Material.FIREWORK_ROCKET).setName("시작!").toItemStack());
            else { whoClicked.getOpenInventory().setItem(23, new ItemBuilder(Material.RED_DYE).setName("시작할수없습니다!").toItemStack());}
        }
        if(_currentPage == 1){
            if(e.getClickedInventory() instanceof PlayerInventory){
                e.setCancelled(false);
            }
            if(slot == 11 || slot == 15){
                e.setCancelled(false);
            }
            if (slot == 13){
                whoClicked.closeInventory();
            }
        }
        if(_currentPage == 2){
            if(e.getClickedInventory() instanceof PlayerInventory){
                e.setCancelled(false);
            }
            if (slot == 13){
                whoClicked.closeInventory();
            }
        }

    }

    @Override
    public void onGUIClose(Player whoClosed){
        if (_currentPage == 0) {
            whoClosed.getInventory().addItem(whoClosed.getOpenInventory().getItem(4));
        }
        if (_currentPage == 1) {
            whoClosed.getInventory().addItem(whoClosed.getOpenInventory().getItem(11));
            whoClosed.getInventory().addItem(whoClosed.getOpenInventory().getItem(15));
        }
    }

    public GambleGUI setPage(int page) {
        if(page < 0)
            _currentPage = 0;
        else if(page > 2)
            _currentPage = 2;
        else
            _currentPage = page;
        return this;
    }

    public GambleGUI setBattedItem(ItemStack battedItem) {
        if (battedItem == null && battedItem.getType().equals(Material.AIR)){
            hasItem = false;
        } else {hasItem = true;}
        this.battedItem = battedItem;
        return this;
    }
}
