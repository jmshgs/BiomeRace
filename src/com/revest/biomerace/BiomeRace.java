package com.revest.biomerace;

import com.revest.biomerace.events.BiomeRaceEvents;
import com.revest.biomerace.commands.BiomeRaceCommands;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Locale;

import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;

public class BiomeRace extends JavaPlugin {



    @Override
    public void onEnable() {
        BiomeRaceCommands commands = new BiomeRaceCommands(this);
        getServer().getPluginManager().registerEvents(new BiomeRaceEvents(), this);
        getCommand("biome").setExecutor(commands);
        getCommand("pplonline").setExecutor(commands);
        getCommand("startrace").setExecutor(commands);
        getCommand("stoprace").setExecutor(commands);
        getCommand("racestatus").setExecutor(commands);
        getCommand("ab").setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[BiomeRace]: Plugin is enabled!");
        //action bar
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    String currentbiome = player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§3"+BiomeRaceCommands.randombiome));

                }
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[BiomeRace]: Plugin is disabled.");
    }
}
