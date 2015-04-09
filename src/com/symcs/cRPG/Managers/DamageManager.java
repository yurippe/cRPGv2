package com.symcs.cRPG.Managers;

import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.CustomEvents.CustomDamageEvent;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.utils.Party;

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
	
	private void onPlayerDamageEvent(EntityDamageEvent event){onPlayerDamageEvent(event, null);}
	private void onPlayerDamageEvent(EntityDamageEvent event, LivingEntity damager){ //Damager can be null
		//TODO split onEntityDamageEvent into these 2 for better readability
		Player p = (Player)event.getEntity();
		PlayerData pdat = plugin.getPlayerManager().getPlayer(p);
		
		
		//IF cause if falldamage, check with status effects
		if(event.getCause() == DamageCause.FALL){
			if(pdat.getStatusEffectManager().takeFallDamage() == false){event.setCancelled(true);}
			else{createCustomDamageEvent(p, event.getDamage());}
			return; //No need for further checking
		}
		
		createCustomDamageEvent(p, damager, event.getDamage());
	}
	
	private void onLivingEntityDamageEvent(EntityDamageEvent event){onLivingEntityDamageEvent(event, null);}
	private void onLivingEntityDamageEvent(EntityDamageEvent event, LivingEntity damager){ //Damager can be null
		//TODO split onEntityDamageEvent into these 2 for better readability
		LivingEntity e = (LivingEntity)event.getEntity();
		
		
		createCustomDamageEvent(e, damager, event.getDamage());
		
	}
	 //native damage
	public void onEntityDamageEvent(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			
			if(event instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent evente = (EntityDamageByEntityEvent) event;
				
				if(plugin.getProjectileManager().isSkillProjectile(evente.getDamager())){
					//the damager was a skill projectile
					Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(evente.getDamager());
					onSkillHitPlayer(damagerSkill, (Player) event.getEntity());
					event.setDamage(0.0);
					return; //Stop here to let the skill handle onhit
				}
				
				else if(evente.getDamager() instanceof LivingEntity){
					
					onPlayerDamageEvent(event, (LivingEntity) evente.getDamager());
					event.setDamage(0.0);
					return;
					
				}
				
			}
				 
			onPlayerDamageEvent(event);
			event.setDamage(0.0);
			return;
			
		}
		
		else if(event.getEntity() instanceof LivingEntity){
			
    		if(event instanceof EntityDamageByEntityEvent){
    			EntityDamageByEntityEvent evente = (EntityDamageByEntityEvent) event;
    			if(plugin.getProjectileManager().isSkillProjectile(evente.getDamager())){
					//the damager was a skill projectile
					Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(evente.getDamager());
					onSkillHitEntity(damagerSkill, (LivingEntity) event.getEntity());
					event.setDamage(0.0);
					return; //Stop here to let the skill handle onhit
				}
    			
				else if(evente.getDamager() instanceof LivingEntity){
					
					onLivingEntityDamageEvent(event, (LivingEntity) evente.getDamager());
					event.setDamage(0.0);
					return;
					
				}
    			
    		}
			
			
			onLivingEntityDamageEvent(event);
			event.setDamage(0.0);
			return;
			
		}
		
		else{return;} //if it is not a mob or player it is probably an item, let this pass
		
		//event.setDamage(0.0); //we manually apply the damage
	}
	
	@Deprecated
	public void OLD_onEntityDamageEvent(EntityDamageEvent event){
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
    			
    			if(evente.getDamager() instanceof Player){
    				Player p_damager = (Player) evente.getDamager();
    				PlayerData damager_dat = plugin.getPlayerManager().getPlayer(p_damager);
    				pdat.getPlayerClass().dealDamage(event.getFinalDamage() * damager_dat.getStatusEffectManager().damageModifier());
    				//TODO stuff
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
	
	@Deprecated
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
			 
		
	}
	
	public void onSkillHitPlayer(Skill skill, Player player){
		//Allows skills to define special behavior on hits
		
		if(skill.ignoreAllies()){
			Party party = plugin.getPartyManager().getParty(skill.getPlayer());
			if(!(party==null)){
				if(party.isMember(player)){
					return;
				}
				
			}else if(skill.getPlayer() == player){return;}
		}
		
		if(skill.getSkillDifferentOnAllies()){
			Party party = plugin.getPartyManager().getParty(skill.getPlayer());
			if(!(party==null)){
				if(party.isMember(player)){
					skill.onSkillHitFriendly(player);
					return;
				}
				
			}else if(skill.getPlayer() == player){skill.onSkillHitFriendly(player);return;}
		}
		

		skill.onSkillHitPlayer(player);
		skill.onSkillHitEntityOrPlayer(player);
	}
	
	public void onSkillHitEntity(Skill skill, LivingEntity entity){
		//Allows skills to define special behavior on hits
		
		skill.onSkillHitEntity(entity);
		skill.onSkillHitEntityOrPlayer(entity);
	}
	
	@Deprecated
	public void invokeEntityDamageByEntityEvent(Entity damager, Entity damagee, DamageCause cause, double damage){
		plugin.getServer().getPluginManager().callEvent(new EntityDamageByEntityEvent(damager, damagee, cause, damage));
	}
	
	@Deprecated
	public void invokeEntityDamageEvent(Entity damagee,DamageCause cause, double damage){
		plugin.getServer().getPluginManager().callEvent(new EntityDamageEvent(damagee, cause, damage));
	}
	
	public void createCustomDamageEvent(Player damagee, LivingEntity damager, double damage){createCustomDamageEvent((LivingEntity) damagee, damager, damage);}
	public void createCustomDamageEvent(Player damagee, Player damager, double damage){createCustomDamageEvent((LivingEntity) damagee, (LivingEntity) damager, damage);}
	public void createCustomDamageEvent(LivingEntity damagee,Player damager, double damage){createCustomDamageEvent(damagee,(LivingEntity) damager, damage);}
	public void createCustomDamageEvent(LivingEntity damagee, LivingEntity damager, double damage){
		CustomDamageEvent event = new CustomDamageEvent(damagee, damager, damage);
		plugin.getServer().getPluginManager().callEvent(event);
	}
	
	public void createCustomDamageEvent(Player damagee, double damage){createCustomDamageEvent((LivingEntity) damagee, damage);}
	public void createCustomDamageEvent(LivingEntity damagee, double damage){
		CustomDamageEvent event = new CustomDamageEvent(damagee, damage);
		plugin.getServer().getPluginManager().callEvent(event);
	}
	
	//TODO
	/*
	 *
	 * REMEMBER: ALL EVENTS LEAVING HERE WILL REMOVE ALL DAMAGE SET TO THE EVENT (You can still cancel the event as a whole)
	 *
	 */

}
