package com.revest.biomerace.config;


import org.bukkit.ChatColor;


import com.revest.biomerace.BiomeRace;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.List;


import static org.bukkit.Bukkit.getServer;


public class textstring {

    static BiomeRace plugin;



    public static void enable(BiomeRace plugin) {
        textstring.plugin = plugin;
        getServer().getConsoleSender().sendMessage(translatedtext("messages.translatedtextload"));
    }

    public static void settoconfig(String path, Object value) {
        plugin.getConfig().set(path, value);
    }
    public static void settoconfigwithint(String path, Integer value) {
        plugin.getConfig().set(path, value);
    }
    public static int getintConfig(String path) {
        Object pathed = plugin.getConfig().get(path);
        return Integer.parseInt(String.valueOf(pathed));
    }

    public static String translatedtext(String s, String... replace) {
        //seems to work just as good as Vanilla Client version and Works good with config.
        String rawtext = plugin.getConfig().getString(s);
        if (rawtext == null) {
            rawtext = "ERROR NULL";
        }



        String newtext = String.format(rawtext, replace);
        return ChatColor.translateAlternateColorCodes('&', newtext);
    }


}
