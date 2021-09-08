package com.revest.biomerace.commands;

import com.revest.biomerace.BiomeRace;
import com.revest.biomerace.actionbar;
import com.revest.biomerace.checks.BiomeRaceCheck;
import com.revest.biomerace.config.config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;


public class BiomeRaceCommands implements CommandExecutor {
    private final BiomeRace plugin;
    public static String randombiome = "";
    private BukkitTask task;

    public BiomeRaceCommands(BiomeRace plugin) {
        getServer().getConsoleSender().sendMessage("Creating BiomeRace Commands Instance");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player Sender = (Player) sender;
        String[] biomes = {"jungle", "desert", "plains", "basalt_deltas", "savanna", "swamp", "taiga",
                "mountains", "forest", "warped_forest", "crimson_forest", "nether_wastes"};

        if (cmd.getName().equalsIgnoreCase("biome")) {
            Sender.sendMessage(ChatColor.AQUA + "You are currently in a " + (ChatColor.AQUA + (Sender.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT)
                    + "" + ChatColor.AQUA + " biome.")));
        }

        if (cmd.getName().equalsIgnoreCase("pplonline")) {
            List<String> playersonline = new ArrayList<>();
            for (Player player : getServer().getOnlinePlayers()) {
                String playername = player.getName();
                playersonline.add(playername);
            }
            String playersonlinestring = String.join(", ", playersonline);
            Sender.sendMessage(ChatColor.AQUA + "Players online: " + playersonlinestring);
        }

        if (cmd.getName().equalsIgnoreCase("startrace")) {
            int randomidx = new Random().nextInt(biomes.length);
            randombiome = biomes[randomidx];
            for (Player player : getServer().getOnlinePlayers()) {
                player.sendTitle(ChatColor.AQUA + "Find a " + randombiome + " biome!", ChatColor.DARK_AQUA + "Find the biome before your opponent!", 10, 100, 20);

            }
            task = new BiomeRaceCheck(Sender, randombiome).runTaskTimer(this.plugin, 0, 100);
            task = new actionbar(randombiome).runTaskTimer(this.plugin, 0, 5);
        }

        if (cmd.getName().equalsIgnoreCase("stoprace")) {
            task.cancel();
            for (Player player : getServer().getOnlinePlayers()) {
                player.sendTitle(ChatColor.AQUA + "The race has been cancelled.", ChatColor.DARK_AQUA + "", 10, 100, 20);
                randombiome = "";
            }
        }

        if (cmd.getName().equalsIgnoreCase("racestatus")) {
            Sender.sendMessage(ChatColor.AQUA + "Looking for a " + randombiome + " biome currently.");
        }

        if (cmd.getName().equalsIgnoreCase("actionbarupdatedelay")) {
            if (strings.length > 0 && !strings[0].startsWith("0")) {
                if (config.isStringInt(strings[0])) {
                    //Set to Config.
                    Sender.sendMessage(ChatColor.AQUA + "okay " + strings[0]);
                }
            }

            Sender.sendMessage(ChatColor.AQUA + "Action");
        }
         return true;
    }


}
