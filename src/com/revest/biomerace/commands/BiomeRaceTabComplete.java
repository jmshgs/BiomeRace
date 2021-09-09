package com.revest.biomerace.commands;

import com.revest.biomerace.BiomeRace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import static org.bukkit.Bukkit.getServer;

import java.util.*;

public class BiomeRaceTabComplete implements TabCompleter {

    public BiomeRaceTabComplete() {
        getServer().getConsoleSender().sendMessage("Creating BiomeRace Tab Instance");
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] strings) {
        if (cmd.getName().equals("updatedelay")) {
            if (strings.length == 1) {
                ArrayList<String> values = new ArrayList<>();


                values.add("ab");
                values.add("rc");

                //Collections.sort(values);

                return values;
            }

        }
        return null;
    }
}







