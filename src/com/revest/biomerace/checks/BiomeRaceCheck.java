package com.revest.biomerace.checks;
import com.revest.biomerace.BiomeRace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

import static org.bukkit.Bukkit.getServer;


public class BiomeRaceCheck extends BukkitRunnable {
    private BiomeRace plugin;
    private final String randombiome;
    private final Player Sender;

    public BiomeRaceCheck(Player Sender, String randombiome, BiomeRace plugin) {
        this.Sender = Sender;
        this.randombiome = randombiome;
        this.plugin = plugin;

    }

    @Override
    public void run() {
        // LESS LAG Bukkit.getConsoleSender().sendMessage("Checking location of players!");
        for (Player player : getServer().getOnlinePlayers()) {
            // Check if this player is in biome
            if (randombiome.equals(player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT))) {
                // Player is in biome - message all players that the game is over
                for (Player p : getServer().getOnlinePlayers()) {
                    p.sendTitle(String.format(plugin.getConfig().getString("messages.firsttobiome"), player.getName()), plugin.getConfig().getString("messages.ggmsg"), 10, 100, 20);
                }
                // Stop loop
                this.cancel();
            }
        }

    }
}