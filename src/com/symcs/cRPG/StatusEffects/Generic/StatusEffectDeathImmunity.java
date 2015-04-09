package com.symcs.cRPG.StatusEffects.Generic;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectDeathImmunity extends StatusEffect{

	public StatusEffectDeathImmunity(int duration) {
		super(duration);
	}
	
	@Override
	public boolean canDie(){return false;}

}
