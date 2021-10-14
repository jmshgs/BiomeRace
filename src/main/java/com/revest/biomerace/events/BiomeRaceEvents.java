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


public class BiomeRaceEvents implements Listener {
    private final BiomeRace plugin;
    public BiomeRaceEvents(BiomeRace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player closeplayer = null;
        boolean whilebool = true;
        Player player = event.getPlayer();
        player.sendMessage("§bWelcome! :D");
        if (!player.getInventory().containsAtLeast(new ItemStack(Material.COMPASS), 1)) {
            player.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        }
        while (whilebool = true) {
            for (Player currentplayer : getServer().getOnlinePlayers()) {
                if (currentplayer == player) {
                    Location location = currentplayer.getLocation();
                    Location location2 = player.getLocation();
                    double distance = location.distance(location2);

                }

            }
        }
    }
}
