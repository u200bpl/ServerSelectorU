package me.u200b.serverselectoru.animations;

import me.u200b.serverselectoru.ServerSelectorU;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Default {
    ServerSelectorU plugin;
    public Default(ServerSelectorU instance) {
        plugin = instance;
    }

    public void defaultAnimation(Player player) {
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(new ServerSelectorU(), new Runnable() {

            public void run() {
                Bukkit.broadcastMessage("1");
                player.sendMessage("Animation starting");
            }

        }, 200L, 200L);
    }

}
