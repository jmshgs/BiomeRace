package com.revest.biomerace;

import com.revest.biomerace.commands.BiomeRaceTabComplete;
import com.revest.biomerace.config.config;
import com.revest.biomerace.config.textstring;
import com.revest.biomerace.events.BiomeRaceEvents;
import com.revest.biomerace.commands.BiomeRaceCommands;
import org.bukkit.plugin.java.JavaPlugin;

import static com.revest.biomerace.config.textstring.translatedtext;


public class BiomeRace extends JavaPlugin {



    @Override
    public void onEnable() {
        textstring.enable(this);
        config config = new config(this);
        BiomeRaceCommands commands = new BiomeRaceCommands(this);
        BiomeRaceTabComplete tabComplete = new BiomeRaceTabComplete(this);
        getServer().getPluginManager().registerEvents(new BiomeRaceEvents(this), this);
        getCommand("biome").setExecutor(commands);
        getCommand("pplonline").setExecutor(commands);
        getCommand("startrace").setExecutor(commands);
        getCommand("stoprace").setExecutor(commands);
        getCommand("racestatus").setExecutor(commands);
        getCommand("updatedelay").setExecutor(commands);
        getCommand("updatedelay").setTabCompleter(tabComplete);
        getServer().getConsoleSender().sendMessage(translatedtext("messages.endabled"));
        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(translatedtext("messages.disabled"));

    }
}
