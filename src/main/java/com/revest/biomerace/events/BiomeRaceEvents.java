package com.revest.biomerace.events;

import com.revest.biomerace.BiomeRace;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;
import static com.revest.biomerace.config.textstring.translatedtext;


public class BiomeRaceEvents implements Listener {
    private final BiomeRace plugin;
    public static Player mainplayer;

    public BiomeRaceEvents(BiomeRace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) { // When a player joins
        mainplayer = event.getPlayer(); // Set "mainplayer" to the player that joined
        mainplayer.sendMessage(translatedtext("messages.playerjoin")); // Print welcome message
        if (!mainplayer.getInventory().containsAtLeast(new ItemStack(Material.COMPASS), 1)) { // Check if player has compass
            mainplayer.getInventory().addItem(new ItemStack(Material.COMPASS, 1)); // If not give the player a compass
        }
    }
}
