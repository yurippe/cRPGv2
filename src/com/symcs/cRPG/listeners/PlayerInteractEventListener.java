package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;



public final class PlayerInteractEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerInteractEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;

 }
 

 @EventHandler
 public void onEvent(PlayerInteractEvent event)
 {

	 //stopthis.plugin.getLogger().info("PlayerInteractEvent: " + event.getAction().toString());
	 Player p = event.getPlayer();
	 if(p.getInventory().getHeldItemSlot() == 0){
		 if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
			 this.plugin.getPlayerManager().getPlayer(p).getPlayerClass().ToggleWeapon();
			 event.setCancelled(true);
		 }
		 else if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){
			 if(!(this.plugin.getPlayerManager().getPlayer(p).getPlayerClass().isArmed)){
				 //open menu
				 plugin.getMenuManager().openMainMenu(p);
				 event.setCancelled(true);
			 }
		 }
	 }
	 
	 if(!(event.isCancelled())){PassReserved(event);}

 }
 
 public void PassReserved(Event e){
	 	if(plugin.getListenerManager().isReserved(this)){
	 		plugin.getListenerManager().PassToReserver(this, e);
	 	}
	 }

}