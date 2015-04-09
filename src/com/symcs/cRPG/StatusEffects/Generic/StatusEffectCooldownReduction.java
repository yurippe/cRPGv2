package com.symcs.cRPG.StatusEffects.Generic;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectCooldownReduction extends StatusEffect{

	private double bonus;

	public StatusEffectCooldownReduction(double bonus, int duration) {
		super(duration);
		this.bonus = bonus;
	}
	
	@Override
	public double cooldownModifier(){return this.bonus;}

}
