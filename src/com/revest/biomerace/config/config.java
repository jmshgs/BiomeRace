package com.revest.biomerace.config;

import com.revest.biomerace.BiomeRace;


import static org.bukkit.Bukkit.getServer;

public class config {

    private static BiomeRace plugin;



    public config(BiomeRace plugin) {
        getServer().getConsoleSender().sendMessage(plugin.getConfig().getString("configinstanceload"));
        config.plugin = plugin;


    }






}
