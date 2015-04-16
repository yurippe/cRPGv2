package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;



public final class PlayerPickupItemEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerPickupItemEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;

 }
 

 @EventHandler
 public void onEvent(PlayerPickupItemEvent event)
 {
	 if(plugin.getPlayerManager().getPlayer(event.getPlayer()).getPlayerClass().isArmed){
		 event.setCancelled(true);
	 }
	 
	 if(!(event.isCancelled())){PassReserved(event);}

 }
 
 public void PassReserved(Event e){
	 	if(plugin.getListenerManager().isReserved(this)){
	 		plugin.getListenerManager().PassToReserver(this, e);
	 	}
	 }

}