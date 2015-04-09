package com.symcs.cRPG.utils;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;

public class DamageCalculator {
	
	private LivingEntity damagee;
	private LivingEntity damager;
	private double raw_damage;
	private cRPG plugin;
	
	//Player takes damage from entity
	//Entity takes damage from Player
	//Entity takes damage from Entity
	//Player takes damage from Player
	
	public DamageCalculator(cRPG plugin, LivingEntity damagee, double raw_damage){this(plugin, damagee, null, raw_damage);}
	public DamageCalculator(cRPG plugin, LivingEntity damagee, LivingEntity damager, double raw_damage){
		this.plugin = plugin;
		this.damagee = damagee;
		this.damager = damager;
		this.raw_damage = raw_damage;
		
	}
	
	public double getFinalDamage(){
		return raw_damage * CalculateBonusAttack() * CalculateBonusDefense();
	}
	
	public double CalculateBonusAttack(){
		if(damager == null){return 1.0;}
		else if(damager instanceof Player){
			
			PlayerData pdat = plugin.getPlayerManager().getPlayer((Player)damager);
			
			double classBonus = pdat.getPlayerClass().getDamageMultiplier();
			double classStatusEffectBonuses = pdat.getStatusEffectManager().damageModifier();
			
			//TODO get additional stuff like status effects / passive skills / stats
			
			double total = classBonus * classStatusEffectBonuses;
			/*
			plugin.getLogger().info(   "Class Bonus Damage = " + Double.toString(classBonus)   );
			plugin.getLogger().info(   "Status Effects Bonus Damage = " + Double.toString(classStatusEffectBonuses)   );
			plugin.getLogger().info(   "Total Bonus Damage = " + Double.toString(total)   );
			*/
			return total;
			
		}
		else{return 1.0;}
	}
	
	public double CalculateBonusDefense(){
		
		if(damagee instanceof Player){
			
			PlayerData pdat = plugin.getPlayerManager().getPlayer((Player)damagee);
			
			double classBonus = pdat.getPlayerClass().getDefenseMultiplier();
			double classStatusEffectBonuses = pdat.getStatusEffectManager().defenseModifier();
			
			//TODO get additional stuff like status effects / passive skills / stats
			
			double total = classBonus * classStatusEffectBonuses;
			
			/*
			plugin.getLogger().info(   "Class Bonus Defense = " + Double.toString(classBonus)   );
			plugin.getLogger().info(   "Status Effects Bonus Defense = " + Double.toString(classStatusEffectBonuses)   );
			plugin.getLogger().info(   "Total Bonus Defense = " + Double.toString(total)   );
			*/
			return total;
			
		}
		else{return 1.0;}
	}
	

	
	
	
}
