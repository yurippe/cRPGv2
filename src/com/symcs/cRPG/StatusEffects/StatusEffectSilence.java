package com.symcs.cRPG.StatusEffects;

import com.symcs.cRPG.BaseClasses.StatusEffect;

public class StatusEffectSilence extends StatusEffect{

	public StatusEffectSilence(int duration) {
		super(duration);
	}
	
	@Override
	public boolean canCast(){return false;}

}
