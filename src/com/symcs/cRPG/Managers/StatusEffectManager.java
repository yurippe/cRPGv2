package com.symcs.cRPG.Managers;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.BaseClasses.StatusEffect;
import com.symcs.cRPG.BaseClasses.StatusEffect.FadeCause;
import com.symcs.cRPG.Tasks.TaskStatusEffectCooldown;
import com.symcs.cRPG.Tasks.UnregisteredTask;

public class StatusEffectManager {
	
	public List<StatusEffect> StatusEffects = new ArrayList<StatusEffect>();
	public HashMap<StatusEffect, TaskStatusEffectCooldown> Cooldowns = new HashMap<StatusEffect, TaskStatusEffectCooldown>();
	private List<StatusEffect> removalQueue = new ArrayList<StatusEffect>();
	private cRPG plugin;
	private PlayerData playerdata;
	
	public StatusEffectManager(cRPG plugin, PlayerData playerdata){
		this.plugin = plugin;
		this.playerdata = playerdata;
	}
	
	private void QueueForRemoval(StatusEffect effect){
		removalQueue.add(effect);

		new UnregisteredTask(plugin){
			private StatusEffectManager manager;
			public UnregisteredTask setManager(StatusEffectManager manager){
				this.manager = manager;
				return this;
			}
			@Override
			public void run(){
				this.manager.CheckRemovalQueue();
			}
		}.setManager(this).runTaskLater(plugin, 1);
	}
	
	public void CheckRemovalQueue(){
		Iterator<StatusEffect> iter = removalQueue.iterator();
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			StatusEffects.remove(effect);
			Cooldowns.remove(effect).cancel();
			iter.remove();
		}
	}
	
	public void addStatusEffect(StatusEffect effect){
		effect.onCast(playerdata, plugin);
		this.StatusEffects.add(effect);
		if(effect.getType() == StatusEffect.Type.TIMED)
		{
			try
			{
			this.Cooldowns.put(effect, new TaskStatusEffectCooldown(effect, this));
			this.Cooldowns.get(effect).runTaskTimer(plugin, 20, 20);
			}
			catch(ConcurrentModificationException e){plugin.getLogger().info("StatusEffect problem");}
		}
		else if(effect.getType() == StatusEffect.Type.CHARGE)
		{
			
		}
		else if(effect.getType() == StatusEffect.Type.CHARGE_LIMITED)
		{
			try
			{
			this.Cooldowns.put(effect, new TaskStatusEffectCooldown(effect, this));
			this.Cooldowns.get(effect).runTaskTimer(plugin, 20, 20);
			}
			catch(ConcurrentModificationException e){plugin.getLogger().info("StatusEffect problem");}
			
		}
		else if(effect.getType() == StatusEffect.Type.CANCEL_ON_DAMAGE){
			
		
			try
			{
			this.Cooldowns.put(effect, new TaskStatusEffectCooldown(effect, this));
			this.Cooldowns.get(effect).runTaskTimer(plugin, 20, 20);
			}
			catch(ConcurrentModificationException e){plugin.getLogger().info("StatusEffect problem");}
		}
		
	}
	
	public void removeStatusEffect(StatusEffect effect){
		this.removeStatusEffect(effect, StatusEffect.FadeCause.UNKNOWN);
		
	}
	
	public void clearStatusEffects(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){
			Cooldowns.remove(iter.next()).cancel();
			iter.remove();
		}
	}
	
	
	public void damageTaken(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.cancelOnDamage()){
				effect.onFade(FadeCause.DAMAGE_TAKEN);
				try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}
			}
		}
	}
	
	public void removeStatusEffect(StatusEffect effect, StatusEffect.FadeCause cause){
		CheckRemovalQueue();
		effect.onFade(cause);
		this.StatusEffects.remove(effect);
		this.Cooldowns.remove(effect);
		/*Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){StatusEffect eff = iter.next(); if(effect == eff){iter.remove();}}
		
		Iterator<StatusEffect> iter2 = this.Cooldowns.keySet().iterator();
		while(iter2.hasNext()){StatusEffect eff = iter.next(); if(effect == eff){iter.remove();}}*/
	}
	
	
	
	public boolean canMove(){ //Doesnt work atm
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){if(!(effect.canMove())){return false;}}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.canMove())){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					return false;
				}}} return true;}
	
	public boolean canCast(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){if(!(effect.canCast())){return false;}}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.canCast())){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					return false;
				}}} return true;}
	
	public boolean canDie(){
		CheckRemovalQueue();
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){if(!(effect.canDie())){return false;}}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.canDie())){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					return false;
				}}} return true;}
	
	public boolean takeFallDamage(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){if(!(effect.takeFallDamage())){return false;}}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.takeFallDamage())){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					return false;
				}}} return true;}
	
	public double damageModifier(){
			Iterator<StatusEffect> iter = this.StatusEffects.iterator();
			double mod_total = 1.0;
			while(iter.hasNext()){
				StatusEffect effect = iter.next();
				if(effect.getType() == StatusEffect.Type.TIMED){mod_total *= effect.damageModifier();}
				if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
					if(!(effect.damageModifier() == 1.0)){
						if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
						try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
						mod_total *= effect.damageModifier();
					}}} return mod_total;}
	
	public double defenseModifier(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		double mod_total = 1.0;
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){mod_total *= effect.defenseModifier();}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.defenseModifier() == 1.0)){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					mod_total *= effect.defenseModifier();
				}}} return mod_total;}
	
	public double cooldownModifier(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		double mod_total = 1.0;
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){mod_total *= effect.cooldownModifier();}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.cooldownModifier() == 1.0)){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					mod_total *= effect.cooldownModifier();
				}}} return mod_total;}
	
	public double healingModifier(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		double mod_total = 1.0;
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){mod_total *= effect.healingModifier();}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.healingModifier() == 1.0)){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					mod_total *= effect.healingModifier();
				}}} return mod_total;}
	
	public double healingTakenModifier(){
		Iterator<StatusEffect> iter = this.StatusEffects.iterator();
		double mod_total = 1.0;
		while(iter.hasNext()){
			StatusEffect effect = iter.next();
			if(effect.getType() == StatusEffect.Type.TIMED){mod_total *= effect.healingTakenModifier();}
			if(effect.getType() == StatusEffect.Type.CHARGE || effect.getType() == StatusEffect.Type.CHARGE_LIMITED){
				if(!(effect.healingTakenModifier() == 1.0)){
					if(effect.tickDuration()){effect.onFade(StatusEffect.FadeCause.CHARGES_SPENT);
					try{iter.remove();}catch(ConcurrentModificationException e){QueueForRemoval(effect);}}
					mod_total *= effect.healingTakenModifier();
				}}} return mod_total;}
		
	
	
}
