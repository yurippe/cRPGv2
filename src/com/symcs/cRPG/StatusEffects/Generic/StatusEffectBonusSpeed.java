package com.symcs.cRPG.StatusEffects.Generic;

import org.bukkit.potion.PotionEffectType;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectBonusSpeed extends StatusEffect{

	public StatusEffectBonusSpeed(int strenght, int timelimit) {
		super(timelimit, Type.CANCEL_ON_DAMAGE);
	}
	
	@Override
	public void onCast(){
		this.player.addPotionEffect(PotionEffectType.SPEED.createEffect(9999, 2));
	}
	
	@Override
	public void  onFade(){
		this.player.removePotionEffect(PotionEffectType.SPEED);
	}
}
