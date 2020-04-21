package com.ems.minecraft.plotmenu.gui;

import com.ems.minecraft.plotmenu.PlotMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PlotMenuGui implements InventoryHolder, Listener {

    private final PlotMenu plotMenu;

    private Inventory inv;

    private final FileConfiguration config;

    private List<ItemStack> clickableItems = new ArrayList<>();

    public PlotMenuGui(PlotMenu plotMenu) {
       this.config = plotMenu.getConfig();
       this.plotMenu = plotMenu;
       initializeGuiWithItems();
    }


    @Override
    public Inventory getInventory() {
        return inv;
    }


    private void initializeGuiWithItems() {
        Set<String> assignedSlots = config.getConfigurationSection("Slots").getKeys(false);
        int numberSlots = config.getInt("NumberSlots");
        plotMenu.getLogger().info("building GUI with "+ assignedSlots.size() +" items in "+numberSlots+" slots");
        inv = Bukkit.createInventory(this, numberSlots , config.getString("InventoryName") );
        for (String assignedSlot : assignedSlots) {
            String itemPath = "Slots." + assignedSlot + ".";
            Material material = Material.getMaterial(config.getString(itemPath+"material"));
            ItemStack item = createGuiItem(material, config.getString(itemPath + "name"), config.getString(itemPath + "description"));
            inv.setItem(Integer.parseInt(assignedSlot), item);
            clickableItems.add(item);
        }
    }

   protected ItemStack createGuiItem(final Material material, final String name, final String... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e)
    {
        if (e.getInventory().getHolder() != this) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR
                || !clickableItems.contains(clickedItem)) return;

        final Player p = (Player) e.getWhoClicked();
        String itemCommandPath = "Slots." + e.getSlot() + ".command";
        p.closeInventory();
        p.chat(config.getString(itemCommandPath));

    }

}
