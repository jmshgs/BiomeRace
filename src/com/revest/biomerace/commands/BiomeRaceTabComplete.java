package com.revest.biomerace.commands;

import com.revest.biomerace.BiomeRace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import static com.revest.biomerace.config.textstring.translatedtext;
import static org.bukkit.Bukkit.getServer;

import java.util.*;

public class BiomeRaceTabComplete implements TabCompleter {

    public BiomeRaceTabComplete(BiomeRace plugin) {
        getServer().getConsoleSender().sendMessage(translatedtext("messages.tabcompleteinstanceload"));
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] strings) {
        if (cmd.getName().toLowerCase(Locale.ROOT).equals("updatedelay")) {
            if (strings.length == 1) {
                ArrayList<String> values = new ArrayList<>();


                values.add("actionbar");
                values.add("racecheck");
                values.add("help");
                values.add("reload");
                values.add("values");

                return values;
            }
            if (strings.length > 1) {
                ArrayList<String> values = new ArrayList<>();
                values.add("0");
                return values;
            }

        }
        return null;
    }
}







