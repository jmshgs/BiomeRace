package com.revest.biomerace.config;


import org.bukkit.ChatColor;


import com.revest.biomerace.BiomeRace;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;


public class textstring {

    static BiomeRace plugin;



    public static void enable(BiomeRace plugin) {
        textstring.plugin = plugin;
        getServer().getConsoleSender().sendMessage(translatedtext("messages.translatedtextload"));
    }

    public static void settoconfig(String path, String value) {
        plugin.getConfig().set(path, value);
    }
    public static void settoconfigwithint(String path, Integer value) {
        plugin.getConfig().set(path, value);
    }

    public static String translatedtext(String s, String... replace) {
        String rawtext = plugin.getConfig().getString(ChatColor.translateAlternateColorCodes('&', s));
        if (rawtext == null) {
            rawtext = "ERROR NULL";
        }
        List<String> strslist = new ArrayList<String>();
        if (replace != null) {
            for (String ssss : replace) {
                strslist.add(ssss);
            }
        }
        else {
                strslist.add("THEREISNOTHING");
            }

        String.format(rawtext, strslist);
        return rawtext;
    }


}
