package com.symcs.cRPG.StatusEffects;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectFallDamageImmunity extends StatusEffect{

	public StatusEffectFallDamageImmunity(int charges, int duration) {
		super(charges, duration);
	}
	
	public boolean takeFallDamage(){
		return false;
	}

}
