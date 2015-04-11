package com.symcs.cRPG.listeners;

//NOTE
//THIS LISTENER EXPLICITLY REQUIRES SPIGOT
//CRAFTBUKKIT WILL NOT SUFFICE

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;

import com.symcs.cRPG.cRPG;

public final class PlayerItemDamageEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerItemDamageEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;
 }
 

 @EventHandler
 public void onEvent(PlayerItemDamageEvent event)
 {
    
	 event.setDamage(0);

 }

}