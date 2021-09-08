package com.revest.biomerace;

import com.revest.biomerace.events.BiomeRaceEvents;
import com.revest.biomerace.commands.BiomeRaceCommands;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


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

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[BiomeRace]: Plugin is disabled.");
    }
    
}
