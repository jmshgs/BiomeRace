package com.revest.biomerace.checks;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.TraderLlama;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Double.MAX_VALUE;
import static org.bukkit.Bukkit.getServer;

public class PlayerTracker {
    public Player thisplayer;
    public Player closestplayer = null;
    public double closestdistance = MAX_VALUE;

    public PlayerTracker(Player player) {
        thisplayer = player;
        for (Player theplayer : getServer().getOnlinePlayers()) { // Loop through players online

            Location thisplayerlocation = thisplayer.getLocation();
            Location locationcheck = theplayer.getLocation();
            double distance = thisplayerlocation.distance(locationcheck);

            if (distance < closestdistance) { // Check if the distance between "mainplayer" and "player2" is greater than the distance between "mainplayer" and "closestplayer"
                // Set "closestdistance" and "closestplayer" to "distance" and "player2"
                closestdistance = distance;
                closestplayer = theplayer;
            }
            player.setCompassTarget(closestplayer.getLocation()); // Set the compass to point to "closestplayer"
        }
    }


    public Player Closestplayer() {
        return closestplayer;
    }
    public double ClosestPlayer_GetDistance() {
        return closestdistance;
    }
}