package com.revest.biomerace;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

public class BiomeRaceActionBar extends BukkitRunnable {
    private final String randombiome;


    public BiomeRaceActionBar(String randombiome) {
        this.randombiome = randombiome;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String currentbiome = player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT);
            if (!player.isSneaking()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§2 Current Biome: " + currentbiome));
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§3 Looking For: " + randombiome));
            }
            if (randombiome.equals(player.getLocation().getBlock().getBiome().toString().toLowerCase(Locale.ROOT))) {
                // Stop loop if player found the biome.
                this.cancel();
            }
        }
    }



}

