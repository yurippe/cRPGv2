package com.symcs.cRPG.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
 
public final class ProjectileHitEventListener implements Listener {

    public cRPG plugin;
	
    public ProjectileHitEventListener(cRPG plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

    }
    
   
    @EventHandler
    public void onEvent(ProjectileHitEvent event)
    {
    	
    	PassReserved(event);
    	if(plugin.getProjectileManager().isSkillProjectile(event.getEntity())){
    		Skill s = plugin.getProjectileManager().getSkillOfProjectile(event.getEntity());
    		s.onProjectileLand(event.getEntity().getLocation());
    	}
    	
    }
    
	 public void PassReserved(Event e){
		 	if(plugin.getListenerManager().isReserved(this)){
		 		plugin.getListenerManager().PassToReserver(this, e);
		 	}
		 }

}
