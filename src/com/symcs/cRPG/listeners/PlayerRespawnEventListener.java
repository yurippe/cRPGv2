package com.symcs.cRPG.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.symcs.cRPG.cRPG;
 
public final class PlayerRespawnEventListener implements Listener {

    public cRPG plugin;
	
    public PlayerRespawnEventListener(cRPG plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    
 
    @EventHandler
    public void onEvent(PlayerRespawnEvent event)
    {
        plugin.getPlayerManager().getPlayer(event.getPlayer()).getPlayerClass().onRespawn();
        
        PassReserved(event);
    }
    
	 public void PassReserved(Event e){
		 	if(plugin.getListenerManager().isReserved(this)){
		 		plugin.getListenerManager().PassToReserver(this, e);
		 	}
		 }

}
