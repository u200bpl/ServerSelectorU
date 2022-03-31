package me.u200b.serverselectoru.inventories;

import me.clip.placeholderapi.PlaceholderAPI;
import me.u200b.serverselectoru.ServerSelectorU;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;

public class ServerSelectorInv implements InventoryHolder {
    ServerSelectorU plugin;
    private Player player;
    private Inventory inventory;

    public ServerSelectorInv(ServerSelectorU instance, Player player) {
        plugin = instance;
        this.player = player;
        this.inventory = Bukkit.createInventory(this, plugin.menuConfig.getInt("menu.slots"), ChatColor.translateAlternateColorCodes('&', plugin.menuConfig.getString("menu.name")));

        for (String string : plugin.menuConfig.getConfigurationSection("menu.items.").getKeys(false)) {
            ItemStack item = new ItemStack(Material.getMaterial(plugin.menuConfig.getString("menu.items." + string + ".material")), 1);
            ItemMeta itemMeta = item.getItemMeta();

            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', plugin.menuConfig.getString("menu.items." + string + ".name"))));
                if (plugin.menuConfig.getString("menu.items." + string + ".lore") != null)
                itemMeta.setLore(Arrays.asList(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', plugin.menuConfig.getString("menu.items." + string + ".lore"))).split("\n")));
            } else {
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.menuConfig.getString("menu.items." + string + ".name")));
                if (plugin.menuConfig.getString("menu.items." + string + ".lore") != null)
                itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', plugin.menuConfig.getString("menu.items." + string + ".lore")).split("\n")));
            }

            item.setItemMeta(itemMeta);
            inventory.setItem(plugin.menuConfig.getInt("menu.items." + string + ".slot"), item);
        }
        player.openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {

        return inventory;
    }
}