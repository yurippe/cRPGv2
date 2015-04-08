package com.symcs.cRPG.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.symcs.cRPG.cRPG;

public class EntityDamageByEntityEventListener implements Listener{

	 public cRPG plugin;
	
	 public EntityDamageByEntityEventListener(cRPG plugin) {
	     plugin.getServer().getPluginManager().registerEvents(this, plugin);
	     this.plugin = plugin;

	 }
	 
	 @EventHandler
	 public void onEvent(EntityDamageByEntityEvent event)
	 {
		 
		 //plugin.getDamageManager().onEntityDamageByEntityEvent(event);
		 //if(!(event.isCancelled())){event.setDamage(0);}
		 
	 }
}
