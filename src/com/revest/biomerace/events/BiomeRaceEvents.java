package com.revest.biomerace.events;

import com.revest.biomerace.BiomeRace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class BiomeRaceEvents implements Listener {
    private final BiomeRace plugin;
    public BiomeRaceEvents(BiomeRace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("§bWelcome! :D");
    }

}
