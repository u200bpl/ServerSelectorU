package me.u200b.serverselectoru.events;

import me.u200b.serverselectoru.ServerSelectorU;
import me.u200b.serverselectoru.inventories.ServerSelectorInv;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InventoryClick implements Listener {
    ServerSelectorU plugin;
    public InventoryClick(ServerSelectorU instance) {
        plugin = instance;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory inv = event.getInventory();
        String command = ".command";

        if (inv.getHolder() instanceof ServerSelectorInv) {
            if (item == null) {
                return;
            }

            event.setCancelled(true);

            for (String string : plugin.menuConfig.getConfigurationSection("menu.items").getKeys(false)) {
                if (event.getSlot() == plugin.menuConfig.getInt("menu.items." + string + ".slot")) {
                    ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                    DataOutputStream output = new DataOutputStream(byteOutput);
                    if (plugin.menuConfig.getString("menu.items." + string + ".server") == "none") {
                        // IGNORE
                    } else {
                        try {
                            output.writeUTF("Connect");
                            output.writeUTF(plugin.menuConfig.getString("menu.items." + string + ".server"));
                            player.sendPluginMessage(plugin, "BungeeCord", byteOutput.toByteArray());
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}