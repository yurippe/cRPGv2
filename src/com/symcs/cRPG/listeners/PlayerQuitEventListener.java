package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;



public final class PlayerQuitEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerQuitEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;

 }
 

 @EventHandler
 public void onEvent(PlayerQuitEvent event)
 {

	 this.plugin.getPlayerManager().getPlayer(event.getPlayer()).onLogout();
     this.plugin.getPlayerManager().removePlayer(event.getPlayer());
     
     this.plugin.getPartyManager().DeclinePartyInvite(event.getPlayer());
     this.plugin.getPartyManager().LeaveParty(event.getPlayer());

 }

}