package com.revest.biomerace.checks;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Double.MAX_VALUE;
import static org.bukkit.Bukkit.getServer;

public class BiomeRaceCompass extends BukkitRunnable {
    private final Player mainplayer;
    private Player closestplayer = null;
    private double closestdistance = MAX_VALUE;

    public BiomeRaceCompass(Player mainplayer) {
        this.mainplayer = mainplayer;
    }

    @Override
    public void run() {
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