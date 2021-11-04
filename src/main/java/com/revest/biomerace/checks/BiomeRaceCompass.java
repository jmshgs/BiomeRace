package com.revest.biomerace.checks;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Double.MAX_VALUE;
import static org.bukkit.Bukkit.getServer;

public class BiomeRaceCompass extends BukkitRunnable {
    private Player mainplayer;
    public Player closestplayer = null;
    public double closestdistance = MAX_VALUE;

    public BiomeRaceCompass(Player mainplayer) {
        this.mainplayer = mainplayer;
    }

    @Override
    public void run() {
        for (Player player2 : getServer().getOnlinePlayers()) { // Loop through players online
            if (player2 != mainplayer) { // Check if "mainplayer" is equal to "player2"
                // Set the distance between the two players
                Location location = mainplayer.getLocation();
                Location location2 = player2.getLocation();
                double distance = location.distance(location2);

                if (distance < closestdistance) { // Check if the distance between "mainplayer" and "player2" is greater than the distance between "mainplayer" and "closestplayer"
                    // Set "closestdistance" and "closestplayer" to "distance" and "player2"
                    closestdistance = distance;
                    closestplayer = player2;
                }
            }
            mainplayer.setCompassTarget(closestplayer.getLocation()); // Set the compass to point to "closestplayer"
        }
    }
}