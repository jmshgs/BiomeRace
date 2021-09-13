package com.revest.biomerace.config;

import com.revest.biomerace.BiomeRace;


import static com.revest.biomerace.config.textstring.translatedtext;
import static org.bukkit.Bukkit.getServer;

public class config {

    private static BiomeRace plugin;



    public config(BiomeRace plugin) {
        config.plugin = plugin;
        getServer().getConsoleSender().sendMessage(translatedtext("messages.configinstanceload"));



    }






}
