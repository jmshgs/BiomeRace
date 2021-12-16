package com.revest.biomerace.commands;

import com.revest.biomerace.BiomeRace;
import com.revest.biomerace.events.BiomeRaceEvents;
import com.revest.biomerace.checks.BiomeRaceActionBar;
import com.revest.biomerace.checks.BiomeRaceCheck;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.revest.biomerace.config.textstring.settoconfigwithint;
import static com.revest.biomerace.config.textstring.translatedtext;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.getWorld;


public class BiomeRaceCommands implements CommandExecutor {
    private final BiomeRace plugin;
    public static String randombiome = "";
    public int checktickdelay = 100;
    public int compasstickdelay = 100;
    private BukkitTask abtask;
    private BukkitTask rctask;
    private BukkitTask cptask;


    public BiomeRaceCommands(BiomeRace plugin) {
        getServer().getConsoleSender().sendMessage("Creating BiomeRace Commands Instance");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        //Create variables
        Player Sender = (Player) sender;
        String[] biomes = {"jungle", "desert", "plains", "basalt_deltas", "savanna", "swamp", "taiga",
                "mountains", "forest", "warped_forest", "crimson_forest", "nether_wastes"};
        String currentbiome = Sender.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT);
        // /biome command - tells the sender the biome they are currently in
        if (cmd.getName().equalsIgnoreCase("biome")) {
            Sender.sendMessage("§bYou are currently in a " + currentbiome.replace("_", " ") + "§b biome."); // Sends the sender a message of what biome they are in
        }
        // /pplonline command - tells the sender which players are online
        if (cmd.getName().equalsIgnoreCase("pplonline")) {
            List<String> playersonline = new ArrayList<>(); // Create an array of players online
            for (Player player : getServer().getOnlinePlayers()) { // Loop through online players
                String playername = player.getName();
                playersonline.add(playername);
            }
            String playersonlinestring = String.join(", ", playersonline);
            Sender.sendMessage(translatedtext("messages.onlineplayers", playersonlinestring));
        }

        if (cmd.getName().equalsIgnoreCase("startrace")) {
            randombiome = "";
            int randomidx = new Random().nextInt(biomes.length);
            randombiome = biomes[randomidx];
            for (Player player : getServer().getOnlinePlayers()) {
                player.sendTitle(translatedtext("messages.racestarttitle", randombiome.replace("_", " ")), translatedtext("messages.racestartsubtitle"), 10, 100, 20);

            }

            rctask = new BiomeRaceCheck(Sender, randombiome).runTaskTimer(this.plugin, 0, checktickdelay);
            abtask = new BiomeRaceActionBar(randombiome).runTaskTimer(this.plugin, 0, checktickdelay);
        }

        if (cmd.getName().equalsIgnoreCase("stoprace")) {
            if (rctask != null) {
                rctask.cancel();
                abtask.cancel();
            }

            for (Player player : getServer().getOnlinePlayers()) {
                player.sendTitle(translatedtext("messages.racecanceltitle"), translatedtext("messages.racecancelsubtitle"), 10, 100, 20);
                randombiome = "";
            }
        }

        if (cmd.getName().equalsIgnoreCase("racestatus")) {
            Sender.sendMessage(translatedtext("messages.racestatus", randombiome.replace("_", " ")));
        }

        if (cmd.getName().equalsIgnoreCase("updatedelay")) {
            if (args.length < 1) {
                Sender.sendMessage(translatedtext("messages.updatedelayinputless"));
            }
            if (args[0].startsWith("check") && args.length > 1) {
                checktickdelay = Integer.parseInt(args[1]);
                settoconfigwithint("delay.checktickdelay", checktickdelay);
                Sender.sendMessage(translatedtext("messages.checkupdatedesription", Integer.toString(checktickdelay)));
            }
            if (args[0].startsWith("compass") && args.length > 1) {
                compasstickdelay = Integer.parseInt(args[1]);
                settoconfigwithint("delay.compasstickdelay", compasstickdelay);
                Sender.sendMessage(translatedtext("messages.compassupdatedesription", Integer.toString(compasstickdelay)));
            }
            if (args[0].startsWith("help")) {
                Sender.sendMessage(translatedtext("messages.updatedelayhelp"));
            }
            if (args[0].startsWith("reload")) {
                plugin.reloadConfig();
                Sender.sendMessage(translatedtext("messages.reloadconfig"));
            } else {
                Sender.sendMessage(translatedtext("messages.updatedelayusage"));
            }

        }
        return true;
    }
}




