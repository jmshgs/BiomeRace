package com.revest.biomerace.commands;

import com.revest.biomerace.BiomeRace;
import com.revest.biomerace.events.BiomeRaceEvents;
import com.revest.biomerace.checks.BiomeRaceActionBar;
import com.revest.biomerace.checks.BiomeRaceCheck;
import com.revest.biomerace.checks.BiomeRaceCompass;
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
    public int actionbartickdelay = 5;
    public int racechecktickdelay = 100;
    public int compasstickdelay = 100;
    private BukkitTask abtask;
    private BukkitTask rctask;
    private BukkitTask cptask;
    private Player mainplayer;
    private Player closestplayer;
    private double closestdistance;


    public BiomeRaceCommands(BiomeRace plugin) {
        getServer().getConsoleSender().sendMessage("Creating BiomeRace Commands Instance");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player Sender = (Player) sender;
        String[] biomes = {"jungle", "desert", "plains", "basalt_deltas", "savanna", "swamp", "taiga",
                "mountains", "forest", "warped_forest", "crimson_forest", "nether_wastes"};
        String currentbiome = Sender.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT);

        if (cmd.getName().equalsIgnoreCase("biome")) {
            Sender.sendMessage("§bYou are currently in a " + currentbiome.replace("_", " ") + "§b biome.");
        }

        if (cmd.getName().equalsIgnoreCase("pplonline")) {
            List<String> playersonline = new ArrayList<>();
            for (Player player : getServer().getOnlinePlayers()) {
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
                player.sendTitle(translatedtext("messages.racestarttitle",randombiome.replace("_", " ")), translatedtext("messages.racestartsubtitle"), 10, 100, 20);

            }
            rctask = new BiomeRaceCheck(Sender, randombiome).runTaskTimer(this.plugin, 0, racechecktickdelay);
            abtask = new BiomeRaceActionBar(randombiome, closestplayer, closestdistance).runTaskTimer(this.plugin, 0, actionbartickdelay);
            cptask = new BiomeRaceCompass(mainplayer).runTaskTimer(this.plugin, 0, compasstickdelay);
        }

        if (cmd.getName().equalsIgnoreCase("stoprace")) {
            if (rctask != null) {
                rctask.cancel();
                abtask.cancel();
                cptask.cancel();
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
            if (args[0].startsWith("actionbar") && args.length > 1) {
                actionbartickdelay = Integer.parseInt(args[1]);
                settoconfigwithint("delay.actionbartickdelay", actionbartickdelay);
                Sender.sendMessage(translatedtext("messages.actionbarupdatedesription", Integer.toString(actionbartickdelay)));
            }
            if (args[0].startsWith("racecheck") && args.length > 1) {
                racechecktickdelay = Integer.parseInt(args[1]);
                settoconfigwithint("delay.racechecktickdelay", racechecktickdelay);
                Sender.sendMessage(translatedtext("messages.racecheckupdatedesription", Integer.toString(racechecktickdelay)));
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
            }
            if (args[0].startsWith("values")) {
                Sender.sendMessage(translatedtext("messages.updatedelayvalues"));
            }
                else {
                Sender.sendMessage(translatedtext("messages.updatedelayinputless"));
                }

            }
        return true;

            }
        }



