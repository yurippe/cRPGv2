package com.symcs.cRPG.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;
 
public final class EntityDeathEventListener implements Listener {

    public cRPG plugin;
	
    public EntityDeathEventListener(cRPG plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

    }
    
 
    @EventHandler
    public void onEvent(EntityDeathEvent event)
    {
    	PassReserved(event);
    	//make sure they dont drop skill items durr
        if(event.getEntity() instanceof Player){
        	PlayerData pdat = plugin.getPlayerManager().getPlayer((Player) event.getEntity());
        	pdat.getPlayerClass().onDeath(event);
        	pdat.getStatusEffectManager().clearStatusEffects();
        	
        }

    }
    
	 public void PassReserved(Event e){
		 	if(plugin.getListenerManager().isReserved(this)){
		 		plugin.getListenerManager().PassToReserver(this, e);
		 	}
		 }

}
