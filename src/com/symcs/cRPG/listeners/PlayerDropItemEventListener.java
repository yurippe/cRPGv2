package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;



public final class PlayerDropItemEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerDropItemEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;

 }
 

 @EventHandler
 public void onEvent(PlayerDropItemEvent event)
 {

	 if(event.getPlayer().getInventory().getHeldItemSlot() == 0){
		 event.setCancelled(true);
	 }
	 
	 if(plugin.getPlayerManager().getPlayer(event.getPlayer()).getPlayerClass().isArmed){
		 event.setCancelled(true);
	 }

	 
 }

}