package com.revest.biomerace;

import com.revest.biomerace.commands.BiomeRaceTabComplete;
import com.revest.biomerace.events.BiomeRaceEvents;
import com.revest.biomerace.commands.BiomeRaceCommands;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class BiomeRace extends JavaPlugin {



    @Override
    public void onEnable() {
        BiomeRaceCommands commands = new BiomeRaceCommands(this);
        BiomeRaceTabComplete tabComplete = new BiomeRaceTabComplete(this);
        getServer().getPluginManager().registerEvents(new BiomeRaceEvents(), this);
        getCommand("biome").setExecutor(commands);
        getCommand("pplonline").setExecutor(commands);
        getCommand("startrace").setExecutor(commands);
        getCommand("stoprace").setExecutor(commands);
        getCommand("racestatus").setExecutor(commands);
        getCommand("updatedelay").setExecutor(commands);
        getCommand("updatedelay").setTabCompleter(tabComplete);
        getServer().getConsoleSender().sendMessage("§b[BiomeRace]: Plugin is enabled!");
        //this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§b[BiomeRace]: Plugin is disabled.");
    }
}
