package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;



public final class InventoryClickEventListener implements Listener {

 public cRPG plugin;
	
 public InventoryClickEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;

 }
 

 @EventHandler
 public void onEvent(InventoryClickEvent event)
 {
	 
	 //just keep your dirty hands off slot 0
	 if(event.getSlot() == 0){
		 if(event.getClickedInventory() == event.getWhoClicked().getInventory()){
		 event.setCancelled(true);
		 }
	}
	 
	 
	//cancel moving of items if armed
	if(event.getWhoClicked() instanceof Player){
		Player player = (Player) event.getWhoClicked();
		if(plugin.getPlayerManager().hasData(player)){
			if(plugin.getPlayerManager().getPlayer(player).getPlayerClass().isArmed){
				 event.setCancelled(true);
			}
		}
	}
	 

	 
 }

}