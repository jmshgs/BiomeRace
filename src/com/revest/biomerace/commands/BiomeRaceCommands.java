package com.revest.biomerace.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import com.revest.biomerace.BiomeRace;
import com.revest.biomerace.checks.BiomeRaceCheck;

import static org.bukkit.Bukkit.*;


public class BiomeRaceCommands implements CommandExecutor {
    private final BiomeRace plugin;
    private String randombiome = "";

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
        String[] biomes = {"jungle", "desert", "plains", "basalt_deltas", "savanna", "swamp", "tagia",
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

        if (cmd.getName().equalsIgnoreCase("race")) {
            boolean p1inbiome = false;
            int randomidx = new Random().nextInt(biomes.length);
            randombiome = biomes[randomidx];
            List<String> playersonline = new ArrayList<>();
            for (Player player : getServer().getOnlinePlayers()) {
                String playername = player.getName();
                playersonline.add(playername);
                player.sendTitle(ChatColor.AQUA + "Find a " + randombiome + " biome!", ChatColor.DARK_AQUA + "Find the biome before your opponent!", 10, 100, 20); }
            BukkitTask task = new BiomeRaceCheck(Sender, randombiome, playersonline).runTaskTimer(this.plugin, 0, 100);
        }

        if (cmd.getName().equalsIgnoreCase("racestatus")) {
            Sender.sendMessage(ChatColor.AQUA + "Looking for a " + randombiome + " biome currently.");
        }
         return true;
    }


}
