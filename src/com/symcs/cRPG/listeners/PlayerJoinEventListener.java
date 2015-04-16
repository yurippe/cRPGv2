package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;



public final class PlayerJoinEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerJoinEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;
 }
 

 @EventHandler
 public void onEvent(PlayerJoinEvent event)
 { 
	 PlayerData pdat = new PlayerData(event.getPlayer(), this.plugin);
     this.plugin.getPlayerManager().addPlayer(event.getPlayer(), pdat);
     pdat.onLogin();
     
     PassReserved(event);

 }
 
 public void PassReserved(Event e){//No proper use for this here? but whatever
	 	if(plugin.getListenerManager().isReserved(this)){
	 		plugin.getListenerManager().PassToReserver(this, e);
	 	}
	 }

}