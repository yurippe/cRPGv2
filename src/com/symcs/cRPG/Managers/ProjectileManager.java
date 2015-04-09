package com.symcs.cRPG.Managers;


import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.metadata.FixedMetadataValue;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public class ProjectileManager {
	
	private cRPG plugin;
	
	public ProjectileManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public boolean isSkillProjectile(Projectile proj){
		return proj.hasMetadata("SkillProjectile");
	}
	
	public boolean isSkillProjectile(Entity proj){
		if(proj instanceof Projectile){
			return isSkillProjectile((Projectile)proj);
		}else{return false;}
	}
	
	public Projectile registerProjectile(Projectile projectile, Skill skill){
		projectile.setMetadata("SkillProjectile", new FixedMetadataValue(plugin, skill));
		return projectile;
	}
	
	
	
	public void unregisterProjectile(Entity projectile){if(projectile instanceof Projectile){unregisterProjectile((Projectile)projectile);}}
	public void unregisterProjectile(Projectile projectile){
		projectile.removeMetadata("SkillProjectile", plugin);
	}
	
	
	
	public Skill getSkillOfProjectile(Projectile projectile){
		if(isSkillProjectile(projectile)){
			return (Skill) projectile.getMetadata("SkillProjectile").get(0).value();
		}else{return null;}
	}
	public Skill getSkillOfProjectile(Entity projectile){
		if(isSkillProjectile(projectile)){
			return (Skill) projectile.getMetadata("SkillProjectile").get(0).value();
		}else{return null;}
	}
		
	
	

}
