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
        }
    }


    public Player ClosestPlayer() {
        return closestplayer;
    }
    public double ClosestPlayer_GetDistance() {
        return closestdistance;
    }
    public void CompassTrackLocation(Location TrackedPlayerLocation) {
        thisplayer.setCompassTarget(TrackedPlayerLocation); // Set the compass to point to "closestplayer"
    }
    public void CompassTrackPlayer(Player TrackedPlayer) {
        thisplayer.setCompassTarget(TrackedPlayer.getLocation()); // Set the compass to point to "closestplayer"
    }
    public void CompassTrackAuto() {
        thisplayer.setCompassTarget(ClosestPlayer().getLocation()); // Set the compass to point to "closestplayer"
    }
}