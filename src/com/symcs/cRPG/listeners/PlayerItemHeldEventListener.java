package com.symcs.cRPG.listeners;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;



public final class PlayerItemHeldEventListener implements Listener {

 public cRPG plugin;
	
 public PlayerItemHeldEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;

 }
 

 @EventHandler
 public void onEvent(PlayerItemHeldEvent event)
 {
    int newSlot = event.getNewSlot();
    PlayerData playerdata = plugin.getPlayerManager().getPlayer(event.getPlayer());
    if(playerdata.getPlayerClass().isArmed){
    	
    	if(playerdata.getStatusEffectManager().canCast()){
    	playerdata.getPlayerClass().UseSkill(newSlot);
    	}
    	event.setCancelled(true);
    	
    }
    

 }

}