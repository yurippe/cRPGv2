package com.symcs.cRPG.StatusEffects.Generic;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectBonusDefense extends StatusEffect{
	
	private double bonus;

	public StatusEffectBonusDefense(double damagemodifier, int duration) {
		super(duration);
		this.bonus = damagemodifier;
	}
	
	@Override
	public double defenseModifier(){return this.bonus;}
	

}

