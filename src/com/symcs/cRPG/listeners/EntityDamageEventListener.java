package com.symcs.cRPG.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import com.symcs.cRPG.cRPG;
 
public final class EntityDamageEventListener implements Listener {

    public cRPG plugin;
	
    public EntityDamageEventListener(cRPG plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

    }
    
 
    @EventHandler
    public void onEvent(EntityDamageEvent event)
    {
    	
    	/*
    	if(event.getEntity() instanceof Player){
    		Player p = (Player)event.getEntity();
    		if(event.getCause() == DamageCause.FALL){
    			if(plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().takeFallDamage() == false){event.setCancelled(true);}
    		}
    		
    		if(!(event.isCancelled())){plugin.getPlayerManager().getPlayer(p).getPlayerClass().onDamageTaken(event);}
    	}
    	*/
    	this.plugin.getDamageManager().onEntityDamageEvent(event);
    	//this.plugin.getLogger().info("EntityDamageEvent " + event.getEntity().getUniqueId().toString() + " " + Double.toString(event.getDamage()));
    	//if(!(event.isCancelled())){event.setDamage(0);}
    	
    	


    }
    
	 public void PassReserved(Event e){//NOT IMPLEMENTED HERE, WILL DO IT IF I EVER HAVE TO
		 	if(plugin.getListenerManager().isReserved(this)){
		 		plugin.getListenerManager().PassToReserver(this, e);
		 	}
		 }

}

