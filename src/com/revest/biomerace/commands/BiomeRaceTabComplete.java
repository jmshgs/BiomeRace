package com.revest.biomerace.commands;

import com.revest.biomerace.BiomeRace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import static org.bukkit.Bukkit.getServer;

import java.util.*;

public class BiomeRaceTabComplete implements TabCompleter {
    private final BiomeRace plugin;

    public BiomeRaceTabComplete(BiomeRace plugin) {
        getServer().getConsoleSender().sendMessage(plugin.getConfig().getString("messages.tabcompleteinstanceload"));
        this.plugin = plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] strings) {
        if (cmd.getName().toLowerCase(Locale.ROOT).equals("updatedelay")) {
            if (strings.length == 1) {
                ArrayList<String> values = new ArrayList<>();


                values.add("ab");
                values.add("rc");
                values.add("help");
                values.add("reload");

                return values;
            }

        }
        return null;
    }
}







