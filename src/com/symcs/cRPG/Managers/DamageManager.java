package com.symcs.cRPG.Managers;

import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Data.PlayerData;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.symcs.cRPG.cRPG;

public class DamageManager {
	
	private cRPG plugin;

	public DamageManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void onEntityDamageEvent(EntityDamageEvent event){
		//Entity taking damage is a player
    	if(event.getEntity() instanceof Player){
    		Player p = (Player)event.getEntity();
    		PlayerData pdat = plugin.getPlayerManager().getPlayer(p);
    		
    		//The event is a EntityDamageByEntityEvent
    		if(event instanceof EntityDamageByEntityEvent){
    			EntityDamageByEntityEvent evente = (EntityDamageByEntityEvent) event;
    			 if(plugin.getProjectileManager().isSkillProjectile(evente.getDamager())){
    					//the damager was a skill projectile
    					Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(evente.getDamager());
    					onSkillHitPlayer(damagerSkill, p);
    					event.setDamage(0.0);
    				}
    		}
    		
    		//Cancel fall damage if you have a status effect that cancels it
    		if(event.getCause() == DamageCause.FALL){
    			if(pdat.getStatusEffectManager().takeFallDamage() == false){event.setCancelled(true);}
    			else{pdat.getPlayerClass().dealDamage(event.getFinalDamage());}
    		}
    		
    		//Checks if player takes lethal damage, and prevents it if there is a status effect that cancels death
    		if(p.getHealth() - event.getFinalDamage() <= 0){ //TODO find way to predict damage taken (or maybe put this very last?)
    														// Or maybe even put it in the dealDamage method in PlayerClass (Want to avoid this)
    			if(!(this.plugin.getPlayerManager().getPlayer((p)).getStatusEffectManager().canDie())){
    				event.setCancelled(true);
    				p.setHealth(1);
    			}
    		}
    		
    		if(!(event.isCancelled())){pdat.getPlayerClass().dealDamage(event.getFinalDamage());}
    	}
    	
    	//Entity taking damage is a living entity
    	else if(event.getEntity() instanceof LivingEntity){
    		LivingEntity e = (LivingEntity)event.getEntity();
    	
    		if(event instanceof EntityDamageByEntityEvent){
    			EntityDamageByEntityEvent evente = (EntityDamageByEntityEvent) event;
    			if(plugin.getProjectileManager().isSkillProjectile(evente.getDamager())){
					//the damager was a skill projectile
					Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(evente.getDamager());
					onSkillHitEntity(damagerSkill, e);
					event.setDamage(0.0);
				}
    		}
    		
    		e.damage(event.getFinalDamage());
    	}
    	
    	//Entity is probably a block or something, do nothing
    	else{return;}
    	//RemoveAll damage from event here:
    	event.setDamage(0.0);
    		
    	
	}
	
	
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
		//Entity taking damage is a player
		if(event.getEntity() instanceof Player){
			 Player p = (Player) event.getEntity();
			 
			 if(plugin.getProjectileManager().isSkillProjectile(event.getDamager())){
					//the damager was a skill projectile
					Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(event.getDamager());
					onSkillHitPlayer(damagerSkill, p);
				}
			 
		
		}
		
		else if(event.getEntity() instanceof LivingEntity){
			LivingEntity e = (LivingEntity) event.getEntity();
			
			 if(plugin.getProjectileManager().isSkillProjectile(event.getDamager())){
					//the damager was a skill projectile
					Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(event.getDamager());
					onSkillHitEntity(damagerSkill, e);
				}
		}
		
	}
	
	public void onSkillHitPlayer(Skill skill, Player player){
		//Allows skills to define special behavior on hits
		skill.onSkillHitPlayer(player);
		skill.onSkillHitEntityOrPlayer(player);
	}
	
	public void onSkillHitEntity(Skill skill, LivingEntity entity){
		//Allows skills to define special behavior on hits
		skill.onSkillHitEntity(entity);
		skill.onSkillHitEntityOrPlayer(entity);
	}
	
	@SuppressWarnings("deprecation")
	public void invokeEntityDamageByEntityEvent(Entity damager, Entity damagee, DamageCause cause, double damage){
		plugin.getServer().getPluginManager().callEvent(new EntityDamageByEntityEvent(damager, damagee, cause, damage));
	}
	
	@SuppressWarnings("deprecation")
	public void invokeEntityDamageEvent(Entity damagee,DamageCause cause, double damage){
		plugin.getServer().getPluginManager().callEvent(new EntityDamageEvent(damagee, cause, damage));
	}
	
	//TODO
	/*
	 *
	 * REMEMBER: ALL EVENTS LEAVING HERE WILL REMOVE ALL DAMAGE SET TO THE EVENT (You can still cancel the event as a whole)
	 *
	 */

}
