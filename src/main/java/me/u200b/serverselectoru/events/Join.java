package me.u200b.serverselectoru.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.u200b.serverselectoru.ServerSelectorU;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Join implements Listener {
    ServerSelectorU plugin;
    public Join(ServerSelectorU instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();

        if (plugin.configConfig.getBoolean("join.clearinv")) {
            player.getInventory().clear();
        }

        if (plugin.configConfig.getBoolean("join.enable")) {

            ItemStack compass = new ItemStack(Material.getMaterial(plugin.configConfig.getString("join.item")), 1);
            ItemMeta itemMeta = compass.getItemMeta();

            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', plugin.configConfig.getString("join.name"))));
                itemMeta.setLore(Arrays.asList(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', plugin.configConfig.getString("join.lore"))).split("\n")));
            } else {
                // EXECUTE WITHOUT PLACEHOLDER CODE
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.configConfig.getString("join.name")));
                itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', plugin.configConfig.getString("join.lore")).split("\n")));
            }

            compass.setItemMeta(itemMeta);
            inv.setItem(plugin.configConfig.getInt("join.slot"), compass);
        }
    }
}