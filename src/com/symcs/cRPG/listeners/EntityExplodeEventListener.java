package com.symcs.cRPG.listeners;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.symcs.cRPG.cRPG;

public final class EntityExplodeEventListener implements Listener {

 public cRPG plugin;
	
 public EntityExplodeEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;


 }
 

 @EventHandler
 public void onEvent(EntityExplodeEvent event)
 {
	 
		if(event.getEntity() instanceof Projectile){
			Projectile proj = (Projectile)event.getEntity();
			if(plugin.getProjectileManager().isSkillProjectile(proj)){
				//is a skill projectile
				//plugin.getProjectileManager().unregisterProjectile(proj);
			    Iterator<Block> iter = event.blockList().iterator();
			    //Removes all nonskill blocks
			    while(iter.hasNext()){
			    	Block b = iter.next();
			    	if(!(b.hasMetadata("SkillBlock"))){iter.remove();}
			    	else
			    	{
			    		b.setType(Material.AIR);
			    	}
			    }
			}
		}

 }

}