package com.revest.biomerace.checks;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

import static com.revest.biomerace.config.textstring.translatedtext;
import static java.lang.Double.MAX_VALUE;

public class BiomeRaceActionBar extends BukkitRunnable {
    private final String randombiome;
    private BiomeRaceCompass compass;

    public BiomeRaceActionBar(String randombiome, BiomeRaceCompass compass) {
        this.randombiome = randombiome;
        this.compass = compass;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) { // Loop through players online
            String currentbiome = player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT); // Set current biome to players' location
            if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) { // Check if player is holding a compass
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translatedtext("messages.trackingplayer", compass.closestplayer.getName(), String.valueOf(compass.closestdistance)))); // Print closest player and the distance to that player
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translatedtext("messages.biomeactionbar", currentbiome.replace("_", " "), randombiome.replace("_", " ")))); // Print biome player is currently in and "randombiome"
            }
            if (randombiome.equals(player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT))) { // Check if player has found biome.
                // Stop loop if player found the biome.
                this.cancel();
            }
        }
    }
}

