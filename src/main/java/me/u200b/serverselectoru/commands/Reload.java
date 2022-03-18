package me.u200b.serverselectoru.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.u200b.serverselectoru.ServerSelectorU;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    ServerSelectorU plugin;
    public Reload(ServerSelectorU instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(sender instanceof Player){
            sender.sendMessage("Only players can excute this command");
        }

        if (player.hasPermission("ss.reload")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    player.sendMessage(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', plugin.messageConfig.getString("reload"))));
                    plugin.reloadConfig();
               }
            } else {
               player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.messageConfig.getString("usage")));
            }
        }
        return false;
    }
}