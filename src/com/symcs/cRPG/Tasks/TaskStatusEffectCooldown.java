package com.symcs.cRPG.Tasks;

import org.bukkit.scheduler.BukkitRunnable;

import com.symcs.cRPG.Managers.StatusEffectManager;
import com.symcs.cRPG.BaseClasses.StatusEffect;

public class TaskStatusEffectCooldown extends BukkitRunnable{
	
	private StatusEffect effect;
	private StatusEffectManager manager;
	
	public TaskStatusEffectCooldown(StatusEffect effect, StatusEffectManager manager){
		this.effect = effect;
		this.manager = manager;
	}
	

	@Override
	public void run() {
		
		//TODO manage player logout while he has effects
		try{
		if(this.effect.tickTimeLimit()){
			manager.removeStatusEffect(effect, StatusEffect.FadeCause.TIMEOUT);
			this.cancel();
		}
		}catch(NullPointerException e){this.cancel();}
	}
	

}
