package com.symcs.cRPG.listeners;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
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
    	/*
    	if(plugin.getProjectileManager().isSkillProjectile(event.getEntity())){
    		LivingEntity hit = getFirstLiving(event.getEntity().getNearbyEntities(0.5, 0.5, 0.5));
    		if(!(hit == null)){
    		if(hit instanceof Player){
				Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(event.getEntity());
				plugin.getDamageManager().onSkillHitPlayer(damagerSkill, (Player) hit);
    		}else{
				Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(event.getEntity());
				plugin.getDamageManager().onSkillHitEntity(damagerSkill, hit);
    		}
    		}
    	}
    	*/
    	

    }
    
    @SuppressWarnings("unused")
	private LivingEntity getFirstLiving(List<Entity> es){
    	for(Entity e:es){
    		if(e instanceof LivingEntity){return (LivingEntity) e;}
    	}
    	return null;
    }

}
