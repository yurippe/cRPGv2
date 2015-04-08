package com.symcs.cRPG.Managers;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public class ProjectileManager {
	
	@SuppressWarnings("unused")
	private cRPG plugin;
	private HashMap<Projectile, Skill> Projectiles = new HashMap<Projectile, Skill>();
	
	public ProjectileManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public boolean isSkillProjectile(Projectile proj){
		return Projectiles.containsKey(proj);
	}
	
	public boolean isSkillProjectile(Entity proj){
		if(proj instanceof Projectile){
			return isSkillProjectile((Projectile)proj);
		}else{return false;}
	}
	
	public void registerProjectile(Projectile projectile, Skill skill){
		Projectiles.put(projectile, skill);
	}
	
	public void unregisterProjectile(Projectile projectile){
		Projectiles.remove(projectile);
	}
	public Skill getSkillOfProjectile(Projectile projectile){
		if(isSkillProjectile(projectile)){
			return Projectiles.get(projectile);
		}else{return null;}
	}
	public Skill getSkillOfProjectile(Entity projectile){
		if(isSkillProjectile(projectile)){
			return Projectiles.get((Projectile) projectile);
		}else{return null;}
	}
		
	
	

}
