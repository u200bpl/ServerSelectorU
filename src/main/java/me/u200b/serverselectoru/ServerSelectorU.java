package me.u200b.serverselectoru;

import me.clip.placeholderapi.PlaceholderAPI;
import me.u200b.serverselectoru.commands.Reload;
import me.u200b.serverselectoru.events.InventoryClick;
import me.u200b.serverselectoru.events.Join;
import me.u200b.serverselectoru.events.OpenMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ServerSelectorU extends JavaPlugin {
    // MESSAGE.YML
    public File messageFile = new File(getDataFolder(), "message.yml");
    public FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messageFile);

    // MENU.YML
    public File menuFile = new File(getDataFolder(), "menu.yml");
    public FileConfiguration menuConfig = YamlConfiguration.loadConfiguration(menuFile);

    // CONFIG.YML
    public File configFile = new File(getDataFolder(), "config.yml");
    public FileConfiguration configConfig = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public void onEnable() {
        // EVENTS
        getServer().getPluginManager().registerEvents( new Join(this), this);
        getServer().getPluginManager().registerEvents( new OpenMenu(this), this);
        getServer().getPluginManager().registerEvents( new InventoryClick(this), this);

        // COMMANDS
        getCommand("serverselectoru").setExecutor(new Reload(this));

        // CHECK IF PLACEHOLDERAPI IS ACTIVE ON SERVER
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            // execute placeholder code
        } else {
            getLogger().warning("Could not find PlaceholderAPI! Download PlaceholderAPI if you want to use placeholders.");
        }

        // GET CUSTOM FILES
        if (!messageFile.exists()) {
            saveResource("message.yml", true);
        }
        if (!menuFile.exists()) {
            saveResource("menu.yml", true);

        }
        if (!configFile.exists()) {
            saveResource("config.yml", true);
        }

        try {
            messageConfig.load(messageFile);
            menuConfig.load(menuFile);
            configConfig.load(configFile);
            return;
        } catch (Exception e) {
            getLogger().warning("Saving files failed: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {

    }
}