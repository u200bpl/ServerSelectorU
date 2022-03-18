package me.u200b.serverselectoru.events;

import me.u200b.serverselectoru.ServerSelectorU;
import me.u200b.serverselectoru.inventories.ServerSelectorInv;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenMenu implements Listener {
    ServerSelectorU plugin;
    public OpenMenu(ServerSelectorU instance) {
        plugin = instance;
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.getItemInHand().getType() == Material.getMaterial(plugin.configConfig.getString("join.item"))) {
            new ServerSelectorInv(plugin, player);
        }
    }
}