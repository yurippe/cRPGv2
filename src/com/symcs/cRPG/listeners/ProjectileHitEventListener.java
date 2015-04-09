package com.symcs.cRPG.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.symcs.cRPG.cRPG;
 
public final class ProjectileHitEventListener implements Listener {

    public cRPG plugin;
	
    public ProjectileHitEventListener(cRPG plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

    }
    
 
    @EventHandler
    public void onEvent(ProjectileHitEvent event)
    {
        /*if(plugin.getProjectileManager().isSkillProjectile(event.getEntity())){
        	plugin.getProjectileManager().unregisterProjectile(event.getEntity());
        }*/
    	
    	
    	if(plugin.getProjectileManager().isSkillProjectile(event.getEntity())){
    		plugin.getLogger().info(event.getEntity().getLocation().toString() + " - Skill arrow: ");
    		plugin.getLogger().info(plugin.getProjectileManager().getSkillOfProjectile(event.getEntity()).toString());
    	}
    	

    }

}
