package com.symcs.cRPG.StatusEffects.Generic;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectBonusDamage extends StatusEffect{
	
	private double bonus;

	public StatusEffectBonusDamage(double damagemodifier, int duration) {
		super(duration);
		this.bonus = damagemodifier;
	}
	
	@Override
	public double damageModifier(){return this.bonus;}
	

}
