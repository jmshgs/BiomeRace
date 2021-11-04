package com.revest.biomerace.checks;

import com.revest.biomerace.BiomeRace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

import static com.revest.biomerace.config.textstring.translatedtext;
import static org.bukkit.Bukkit.getServer;


public class BiomeRaceCheck extends BukkitRunnable {
    private final String randombiome;
    private final Player Sender;

    public BiomeRaceCheck(Player Sender, String randombiome) {
        this.Sender = Sender;
        this.randombiome = randombiome;

    }

    @Override
    public void run() {
        for (Player player : getServer().getOnlinePlayers()) { // Loop through players online
            if (randombiome.equals(player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT))) { // Check if player is in biome
                // Player is in biome - message all players that the game is over
                for (Player p : getServer().getOnlinePlayers()) {
                    p.sendTitle(translatedtext("messages.firsttobiome", player.getName()), translatedtext("messages.ggmsg", null), 10, 100, 20);
                }
                // Stop the loop
                this.cancel();
            }
        }

    }
}