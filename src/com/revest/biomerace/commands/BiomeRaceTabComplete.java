package com.revest.biomerace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class BiomeRaceTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] strings) {
        if (cmd.getName() == "actionbarupdatedelay") {
            if (strings.length == 1) {
                ArrayList<String> values = new ArrayList<String>();

                if (strings[0].equals("")) {

                }
            }
        }









        return null;
    }
}
