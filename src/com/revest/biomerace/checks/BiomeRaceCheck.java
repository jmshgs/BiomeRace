package com.revest.biomerace.checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import java.util.Locale;

import static org.bukkit.Bukkit.getServer;


public class BiomeRaceCheck extends BukkitRunnable {
    private String randombiome;
    private Player Sender;
    private List playersonline;

    public BiomeRaceCheck(Player Sender, String randombiome, List playersonline) {
        this.Sender = Sender;
        this.randombiome = randombiome;
        this.playersonline = playersonline;
    }

    @Override
    public void run() {
        Bukkit.getConsoleSender().sendMessage("Checking location of players!");
        List<String> playersonline = new ArrayList<>();
        for (Player player : getServer().getOnlinePlayers()) {
            // Check if this player is in biome
            if (randombiome.equals(player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT))) {
                // Player is in biome - message all players that the game is over
                for (Player p : getServer().getOnlinePlayers()) {
                    p.sendTitle(ChatColor.AQUA + player.getName() + "found the biome first!", ChatColor.DARK_AQUA + "ggs", 10, 100, 20);
                }
                // Stop loop
                this.cancel();
            }
        }

    }
}