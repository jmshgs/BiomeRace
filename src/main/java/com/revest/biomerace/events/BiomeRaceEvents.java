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

import static java.lang.Double.MAX_VALUE;
import static org.bukkit.Bukkit.getServer;


public class BiomeRaceEvents implements Listener {
    private final BiomeRace plugin;
    public BiomeRaceEvents(BiomeRace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player closestplayer = null;
        double closestdistance = MAX_VALUE;

        Player mainplayer = event.getPlayer();
        mainplayer.sendMessage("§bWelcome! :D");
        if (!mainplayer.getInventory().containsAtLeast(new ItemStack(Material.COMPASS), 1)) {
            mainplayer.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        }
        for (Player player2 : getServer().getOnlinePlayers()) {
            if (player2 != mainplayer) {
                Location location = mainplayer.getLocation();
                Location location2 = player2.getLocation();
                double distance = location.distance(location2);

                if (distance < closestdistance) {
                    closestdistance = distance;
                    closestplayer = player2;
                }
            }
            mainplayer.setCompassTarget(closestplayer.getLocation());
        }
    }
}